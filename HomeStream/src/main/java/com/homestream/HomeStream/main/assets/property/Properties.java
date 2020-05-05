package com.homestream.HomeStream.main.assets.property;

import com.homestream.HomeStream.main.assets.Loader;
import com.homestream.HomeStream.web.assets.TEMP;

public class Properties
{
    /**
     * General Properties
     */

    private static Loader loader = new Loader();
    private static PropertiesLoader propertiesLoader = new PropertiesLoader();

    /**
     * Main Properties
     */
    public static final String TITLE = "HomeStream";
    public static final String[] MOTTO = propertiesLoader.getAsArray("general.motto");

    public static final String DOMAIN = propertiesLoader.getAsString("general.domain");
    public static String[] LANGUAGES = propertiesLoader.getAsArray("general.lang.names");
    public static String[] LANGUAGE_TAGS = propertiesLoader.getAsArray("general.lang.tags");
    public static String LANG = propertiesLoader.getAsString("general.lang");

    public static boolean REPLACE_UPLOADED_FILES = propertiesLoader.getAsBoolean("general.replace_uploaded_files");

    private static final byte VERSION_MAJOR = 0;
    private static final byte VERSION_MINOR = 5;
    private static final short VERSION_PATCH = 3;
    private static final String  VERSION_DATE = "26.01.2020";
    public static final String VERSION = VERSION_MAJOR + ":" + VERSION_MINOR + ":" + VERSION_PATCH + " - " + VERSION_DATE ;

    /**
     * Static Content Properties
     */
    public static final byte THEME = propertiesLoader.getAsByte("general.theme");

    private static final String CSS_PATH = "css/";
    public static final String[] MAIN_CSS = new String[]
            {CSS_PATH + "main.css", CSS_PATH + "darkMain.css"};
    public static final String[] COLOR_CSS = new String[]
            {CSS_PATH + "color.css", CSS_PATH + "darkColor.css"};
    public static final String[] ICONS_CSS = new String[]
            {"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css", CSS_PATH + "darkIcon.css"};
    public static final String[] CONTENT_CSS = new String[]
            {CSS_PATH + "content.css", CSS_PATH + "darkContent.css"};
    public static final String[] NAVIGATION_CSS = new String[]
            {CSS_PATH + "navigation.css", CSS_PATH + "darkNavigation"};
    public static final String[] BACKGROUND_CSS = new String[]
            {CSS_PATH + "background.css", CSS_PATH + "darkBackground.css"};

    private static final String JS_PATH = "js/";
    public static final String MAIN_JS = JS_PATH + "main.js";
    public static final String CONTENT_JS = JS_PATH + "content.js";
    public static final String AUDIO_JS = JS_PATH + "audio.js";
    public static final String VIDEO_JS = JS_PATH + "video.js";

    /**
     * Dynamic Content Properties
     */
    private static final String TEMP_PATH = "./res/template/";
    public static final TEMP[] CONTENT_NAVIGATION = new TEMP[]
            {new TEMP(TEMP_PATH + "defaultNav")};
    public static final TEMP[] CONTENT_TITLE = new TEMP[]
            {new TEMP(TEMP_PATH + "defaultTitle")};
    public static final TEMP[] AUDIO_TITLE = new TEMP[]
            {new TEMP(TEMP_PATH + "defaultAudioTitle")};
    public static final TEMP[] VIDEO_TITLE = new TEMP[]
            {new TEMP(TEMP_PATH + "defaultVideoTitle")};
    public static final TEMP[] LOGIN = new TEMP[]
            {new TEMP(TEMP_PATH + "defaultUser")};
    public static final TEMP[] SEARCH = new TEMP[]
            {new TEMP(TEMP_PATH + "defaultSearch")};
    public static final TEMP[] IMG_TITLE = new TEMP[]
            {new TEMP(TEMP_PATH + "defaultImageTitle")};
    public static final TEMP[] CONTENT_ELEMENT = new TEMP[]
            {new TEMP(TEMP_PATH + "defaultContentElement")};
    public static final TEMP[] CONTENT_ALL = new TEMP[]
            {new TEMP(TEMP_PATH + "defaultContentAll")};
    public static final TEMP[] UPLOAD = new TEMP[]
            {new TEMP(TEMP_PATH + "defaultUpload")};

    public static final TEMP FOOT = new TEMP(TEMP_PATH + "foot");

    public static final String CONTENT_TITLE_LAST_UPLOADS_ID = "LAST_UPLOAD";
    public static final String CONTENT_TITLE_LAST_STREAMS_ID = "LAST_STREAM";
    public static final String CONTENT_TITLE_FAVORITES_ID = "FAVORITES";
    public static final String CONTENT_TITLE_SEARCH_RESULT_ID = "RESULT";
}
