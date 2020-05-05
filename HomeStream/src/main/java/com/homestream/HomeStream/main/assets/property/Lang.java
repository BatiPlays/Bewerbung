package com.homestream.HomeStream.main.assets.property;

public class Lang
{
    /**
     * Language Properties
     */

    private static LangLoader loader = new LangLoader();

    public static final String FILTER = loader.getValue("NAV.FILTER");

    public static final String VIDEO = loader.getValue("NAV.VIDEO");
    public static final String IMAGE = loader.getValue("NAV.IMAGE");
    public static final String MUSIC = loader.getValue("NAV.MUSIC");
    public static final String SEARCH = loader.getValue("NAV.SEARCH");
    public static final String HOME = loader.getValue("NAV.HOME");

    public static final String LOGIN = loader.getValue("USER.LOGIN");
    public static final String QUIT = loader.getValue("USER.QUIT");
    public static final String REGISTER = loader.getValue("USER.REGISTER");
    public static final String USERNAME = loader.getValue("USER.USERNAME");
    public static final String PASSWORD = loader.getValue("USER.PASSWORD");

    public static final String CLICK_TO_CLOSE = loader.getValue("MEDIA.CLICK_TO_CLOSE");
    public static final String EXT_SEARCH = loader.getValue("NAV.SEARCH.EXT_SEARCH");
    public static final String HINT_LIST_SEPARATOR = loader.getValue("MEDIA.HINT_LIST_SEPARATOR");

    public static final String TITLE_NAME_SEARCH = loader.getValue("NAV.SEARCH.TITLE_NAME_SEARCH");
    public static final String PLAYLIST_NAME_SEARCH = loader.getValue("NAV.SEARCH.PLAYLIST_NAME_SEARCH");
    public static final String ARTIST_ACTOR_SEARCH = loader.getValue("NAV.SEARCH.ARTIST_ACTOR_SEARCH");
    public static final String OWNER_SEARCH = loader.getValue("NAV.SEARCH.OWNER_SEARCH");
    public static final String RELEASE_SEARCH = loader.getValue("NAV.SEARCH.RELEASE_SEARCH");
    public static final String GENRE_SEARCH = loader.getValue("NAV.SEARCH.GENRE_SEARCH");
    public static final String TAGS_SEARCH = loader.getValue("NAV.SEARCH.TAGS_SEARCH");

    public static final String CONTENT_TITLE_SEARCH_RESULT = loader.getValue("NAV.SEARCH.CONTENT_TITLE_SEARCH_RESULT");
}
