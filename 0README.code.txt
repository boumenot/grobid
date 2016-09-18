== Code that Reaches into GrobidProperty ==
 1. GrobidModels.java
   * ctor -> .getModelPath(...);
 2. TaggerFatory.java
   * static synchronized GenericTagger getTagger(GrobidModels model) -> getGrobidCRFEngine();
 3. FullTextParsers.java    
   * ctor -> super(GrobidModels.FULLTEXT);
   * ctor -> GrobidProperties.getTempPath();
 4. HeaderParser.java
   * ctor -> GrobidProperties.getTempPath();
   * createTrainingHeader -> GrobidProperties.FILE_ENDING_TEI_HEADER
 5. Document.java
   * getPdf2xml(...) -> GrobidProperties.getPdf2XMLPath()
   * pdf2xml(...)    -> GrobidProperties.isContextExecutionServer()

== Code to Fix ==
 1. HeaderParser.java
   * createTrainingHeader -> creating an XML document using a StreamWriter, use an XSD instead
 2. Pdf2Xml - wrap this up in a class.
   * either it is native or thread based depending on if it executes as a server or not - ugh.
   * why isn't it just thread based?
 3. Document.java
   * addTokenizedDocument() -> reads an XML representation of a PDF using PDF2XMLSaxParser(),
     and gets the tokens() from the document.  Tokens are just a List<string>.
 4. Create TempFile to do cleanup for pdftoxml.exe
   * implements Closeable
 5. LibraryLoader.load()
   * update this to explicitly load a library
   * update this to a take a path to the DLL to load.


AbstractEngineFactory.java ->
  public static void init() {
  	GrobidProperties.getInstance();
  	LibraryLoader.load();
  }

public enum GrobidModels {
    AFFIILIATON_ADDRESS("affiliation-address"),
    SEGMENTATION("segmentation"),
    CITATION("citation"),
    REFERENCE_SEGMENTER("reference-segmenter"),
    DATE("date"),
    EBOOK("ebook"),
    ENTITIES_CHEMISTRY("entities/chemistry"),
    //	ENTITIES_BIOTECH("entities/biotech"),
    FULLTEXT("fulltext"),
    HEADER("header"),
    NAMES_CITATION("name/citation"),
    NAMES_HEADER("name/header"),
    PATENT_PATENT("patent/patent"),
    PATENT_NPL("patent/npl"),
    PATENT_ALL("patent/all"),
    PATENT_STRUCTURE("patent/structure"),
    PATENT_EDIT("patent/edit"),
    //	ENTITIES_CHEMISTRY("chemistry"),
    ENTITIES_NER("ner"),
	ENTITIES_NERSense("nersense"),
    QUANTITIES("quantities"),
    ENTITIES_BIOTECH("bio");
}

public class Config {
  public static string PdfToXml { get; set; }

  public string GrobidHomePath { get; set; }
  -> public string FolderNameModels { get; set; }

  public string FileNameModel { get; set; }
  
  public string PROP_TMP_PATH { get; set; }
  public string PROP_CROSSREF_ID { get; set; }
  public string PROP_CROSSREF_PW { get; set; }
  public string PROP_CROSSREF_HOST { get; set; }
  public string PROP_CROSSREF_PORT { get; set; }
  public string PROP_PROXY_HOST { get; set; }

  // Name of the connection
  public string PROP_MYSQL_DB_NAME { get; set; }
  public string PROP_MYSQL_DB_USERNAME { get; set; }
  public string PROP_MYSQL_DB_PW { get; set; }
  public string PROP_MYSQL_DB_HOST { get; set; }
  public string PROP_MYSQL_DB_PORT { get; set; }

  public string PROP_NB_THREADS { get; set; }

  public string PROP_USE_LANG_ID { get; set; }
  public string PROP_LANG_DETECTOR_FACTORY { get; set; }
  public string PROP_RESOURCE_INHOME { get; set; }
  
  public string PROP_3RD_PARTY_PDF2XML { get; set; }

  public bool PROP_GROBID_IS_CONTEXT_SERVER { get; set; }

  public int PROP_GROBID_MAX_CONNECTIONS { get; set; }
  public int PROP_GROBID_POOL_MAX_WAIT { get; set; }

  public TimeSpan getPoolMaxWait {
    get { return TimeSpan.FromSeconds(this.PROP_GROBID_POOL_MAX_WAIT); }
  }
  
  public string getModelPath(final GrobidModel model) {
    var path Path.Combine(
      this.FolderNameModels,
      model.FolderName,
      this.FileNameModel);

    return Path.ChangeFilenameExtension(
      path,
      grobidCrfEngine.getExt());
  }

  
  /*training */ public string getTemplatePath(string resourcesDir, GrobidModel model) {
    var path = Path.Combine(
      resourcesDir,
      "dataset",
      model.FolderName,
      "crfpp-templates",
      model.TemplateName);

    if (!File.Exists(path)) {
      path = Path.Combine(
        "resources",                           
        "dataset",
        model.FolderName,
        "crfpp-templates",
        model.TemplateName);
    }
 
    return path;
  }

  public string LexiconPath {
    get { return Path.Combine(this.GrobidHomePath, "lexicon"); }
  }

  public string LanguageDetectionResourcePath {
    get { return Path.Combine(this.GrobidHomePath, "language-detection"); }
  }

  public string PROP_NATIVE_LIB_PATH { get; set; }
  -> 
}

public String pdf2xml(
       boolean hasTimeout, 
       boolean force, 
       int startPage,
       int endPage, 
       String pdfPath, 
       String tmpPath, 
       boolean full)

HeaderParser.java:
createTrainingHeader():
            pathXML = doc.pdf2xml(true, false, startPage, endPage, inputFile, tmpPath.getAbsolutePath(), false);
            // timeout,
            // no force pdf reloading
            // pathPDF is the pdf file, tmpPath is the tmp directory for the
            // lxml file,
            // path is the resource path
            // and we do not extract images in the PDF file
            if (pathXML == null) {
                throw new GrobidException("PDF parsing fails");
            }
            doc.setPathXML(pathXML);
            doc.addTokenizedDocument();

            if (doc.getBlocks() == null) {
                throw new GrobidException("PDF parsing resulted in empty content");
            }

            String header = doc.getHeaderFeatured(true, true, true);
            List<String> tokenizations = doc.getTokenizationsHeader();

HeaderParser.java:
  processing2()
            // int startPage = 0;
            // //int endPage = 1;
            // int endPage = 2;
            pathXML = doc.pdf2xml(true, false, startPage, endPage, input, tmpPath.getAbsolutePath(), false);
            // timeout,
            // no force pdf reloading
            // input is the pdf file, tmpPath is the tmp directory for the lxml
            // file,
            // path is the resource path
            // and we do not extract images in the PDF file
            if (pathXML == null) {
                throw new GrobidException("PDF parsing fails");
            }
            doc.setPathXML(pathXML);
            doc.addTokenizedDocument();

            if (doc.getBlocks() == null) {
                throw new GrobidException("PDF parsing resulted in empty content");
            }

            String tei = processingHeaderBlock(consolidate, doc, resHeader);
            return new ImmutablePair<String, Document>(tei, doc);


Segmentation.java:
  createTrainingSegmentation()
            int startPage = -1;
            int endPage = -1;
            File file = new File(inputFile);
            if (!file.exists()) {
                throw new GrobidResourceException("Cannot train for fulltext, because file '" +
                        file.getAbsolutePath() + "' does not exists.");
            }
            String PDFFileName = file.getName();
            pathXML = doc.pdf2xml(true, false, startPage, endPage, inputFile, tmpPath.getAbsolutePath(), true);
            //with timeout,
            //no force pdf reloading
            // pathPDF is the pdf file, tmpPath is the tmp directory for the lxml file,
            // path is the resource path
            // and we don't extract images in the pdf file
            if (pathXML == null) {
                throw new Exception("PDF parsing fails");
            }
            doc.setPathXML(pathXML);
            doc.addTokenizedDocument();

            if (doc.getBlocks() == null) {
                throw new Exception("PDF parsing resulted in empty content");
            }

Segmentation.java:
  processing()

 int startPage = -1;
            int endPage = -1;
			if (headerMode) {
				startPage = 0;
				endPage = 3;
			}
            pathXML = doc.pdf2xml(true, false, startPage, endPage, input, tmpPath.getAbsolutePath(), false);
            //with timeout,
            //no force pdf reloading
            // input is the pdf absolute path, tmpPath is the temp. directory for the temp. lxml file,
            // path is the resource path
            // and we don't extract images in the PDF file
            if (pathXML == null) {
                throw new GrobidResourceException("PDF parsing fails, " +
                        "because path of where to store xml file is null.");
            }
            doc.setPathXML(pathXML);
            List<String> tokenizations = doc.addTokenizedDocument();

            if (doc.getBlocks() == null) {
                throw new GrobidException("PDF parsing resulted in empty content");
            }

BookStructureParser.java:
  createTrainingFullTextEbook()
            int startPage = -1;
            int endPage = -1;
            File file = new File(inputFile);
            String PDFFileName = file.getName();
            pathXML = doc.pdf2xml(false, false, startPage, endPage, inputFile, tmpPath.getAbsolutePath(), false); //without timeout,
            //no force pdf reloading
            // inputFile is the pdf file, tmpPath is the tmp directory for the lxml file,
            // path is the resource path
            // we do not extract the images in the pdf file
            if (pathXML == null) {
                throw new Exception("PDF parsing fails");
            }
            doc.setPathXML(pathXML);
            doc.addTokenizedDocument();

            if (doc.getBlocks() == null) {
                throw new Exception("PDF parsing resulted in empty content");
            }


ReferenceExtractor::extractAllReferencesPDFFile()
    PatentDocument doc = new PatentDocument();
    int startPage = -1;
    int endPage = -1;
    tmpPath = GrobidProperties.getTempPath().getAbsolutePath();
    pathXML = doc.pdf2xml(true, false, startPage, endPage, inputFile, tmpPath, false); //with timeout,
    // no force pdf reloading
    // inputFile is the pdf file, tmpPath is the tmp directory for the lxml file,
    // and we do not extract images in the PDF file
    if (pathXML == null) {
        throw new GrobidException("PDF parsing fails");
    }
    doc.setPathXML(pathXML);

    if (doc.getBlocks() == null) {
        throw new GrobidException("PDF parsing resulted in empty content");
    }
    description = doc.getAllBlocksClean(25, -1);
