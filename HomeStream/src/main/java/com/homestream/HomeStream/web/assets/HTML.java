package com.homestream.HomeStream.web.assets;

import com.homestream.HomeStream.entity.*;
import com.homestream.HomeStream.main.assets.Assets;
import com.homestream.HomeStream.main.assets.property.Lang;
import com.homestream.HomeStream.main.assets.property.Properties;
import com.homestream.HomeStream.main.exception.FileEndingException;
import com.homestream.HomeStream.web.generator.WebGenerator;

import java.io.IOException;
import java.util.List;

public class HTML
{
    /**
     * Class to store HTML
     *
     * @author S. Schulze
     * @last_update 5.11.19
     */

    String search;
    String data;
    String prep;

    String elements;

    int counter = 0;
    boolean isPrep = false;

    WebGenerator generator = new WebGenerator();

    /**
     * Function to Create Data
     * @param file
     * @throws IOException
     */
    public HTML(String file) throws IOException
    {
        if(file.endsWith(".script"))
        {
            data = generator.generate(file);
        }
        else throw new FileEndingException(".script");
    }

    /**
     * Function to Prepare Data
     * @param Edata
     * @param search
     */
    public void prepare(List<? extends IEntity> Edata, String search)
    {
        counter = 0;

        isPrep = true;
        prep = data;
        elements = "<div class=\"list BOTTOM_SMAL\">";

        if(search.equals("music")) this.search = Lang.MUSIC;
        else if(search.equals("image")) this.search = Lang.IMAGE;
        else if(search.equals("film")) this.search = Lang.VIDEO;
        else if(search.equals("home")) this.search = Lang.HOME;
        else if(search.startsWith("ext")) this.search = Assets.decryptQuery(search);
        else this.search = Lang.CONTENT_TITLE_SEARCH_RESULT + " '" + search + "'";

        for (int i = 0; i < Edata.size(); i++)
        {
            if(counter == 5)
            {
                counter = 0;
                elements += "</div><div class=\"list BOTTOM_SMAL\">";
            }

            if(Edata.get(i).getClass().toString().contains("MusicEntity"))
            {
                MusicEntity me = ((MusicEntity) Edata.get(i));
                elements += "\n" +
                        "<div class=\"cover\">\n" +
                        "<img name=\"cover\" src=\"data/" + me.getThumbnailName() + "\">\n" +
                        "<div name=\"coverText\">\n" +
                        "<h2 name=\"coverText\">" + me.getName() + "</h2>\n" +
                        "<h2><a><i name=\"audio\" id=\"" + me.getFileName() + "\" title=\"" + me.getName() + "\" class=\"fa fa-fw fa-play mediaButton\" id=\"user\"></i></a>"+
                        "<a data=\"" + me.getId() + "\"><i class=\"fa fa-fw fa-info mediaButton\" id=\"user\"></i></a></h2>\n" +
                        "</div>\n" +
                        "</div>";
            }
            else if(Edata.get(i).getClass().toString().contains("FilmEntity"))
            {
                FilmEntity fe = ((FilmEntity) Edata.get(i));
                elements += "\n" +
                        "<div  class=\"cover\">\n" +
                        "<img name=\"cover\" src=\"data/" + fe.getThumbnailName() + "\">\n" +
                        "<div name=\"coverText\">\n" +
                        "<h2 name=\"coverText\">" + fe.getName() + "</h2>\n" +
                        "<h2><a><i name=\"video\" id=\"" + fe.getFileName() + "\" title=\"" + fe.getName() + "\" class=\"fa fa-fw fa-play mediaButton\" id=\"user\"></i></a>\t<a data=\"" + fe.getId() + "\"><i class=\"fa fa-fw fa-info mediaButton\" id=\"user\"></i></a></h2>\n" +
                        "</div>\n" +
                        "</div></a>";
            }
            else if(Edata.get(i).getClass().toString().contains("ImageEntity"))
            {
                ImageEntity ie = ((ImageEntity) Edata.get(i));
                elements += "\n" +
                        "<div class=\"cover\">\n" +
                        "<img name=\"cover\" src=\"data/" + ie.getThumbnailName() + "\">\n" +
                        "<div name=\"coverText\">\n" +
                        "<h2 name=\"coverText\">" + ie.getName() + "</h2>\n" +
                        "<h2><a><i name=\"img\" id=\"" + ie.getFileName() + "\" title=\"" + ie.getName() + "\" class=\"fa fa-fw fa-expand mediaButton\" id=\"user\"></i></a>\t<a data=\"" + ie.getId() + "\"><i class=\"fa fa-fw fa-info mediaButton\" id=\"user\"></i></a></h2>\n" +
                        "</div>\n" +
                        "</div></a>";
            }
            else // if(Edata.get(i).getClass().toString().contains("PlaylistEntity"))
            {
                ImageEntity pe = ((ImageEntity) Edata.get(i));
                elements += "\n" +
                        "<div class=\"cover\">\n" +
                        "<img name=\"cover\" src=\"data/" + pe.getFileName() + "\">\n" +
                        "<div name=\"coverText\">\n" +
                        "<h2 name=\"coverText\">" + pe.getName() + "</h2>\n" +
                        "<h2><a><i name=\"img\" id=\"" + pe.getFileName() + "\" title=\"" + pe.getName() + "\" class=\"fa fa-fw fa-expand mediaButton\" id=\"user\"></i></a></h2>\n" +
                        "</div>\n" +
                        "</div></a>";
            }

            counter++;
        }

        elements += "</div>";
    }

    /**
     * (Default) Decrypt Language and Content tags and Return Data
     * @return
     */
    public String get()
    {
        String out = data;

        if(isPrep) out = Assets.replaceAll(prep,"@ELEMENTS", elements);

        out = Assets.replaceAll(out,"@HOME", Lang.HOME);
        out = Assets.replaceAll(out,"@FILTER", Lang.FILTER);
        out = Assets.replaceAll(out,"@VIDEO", Lang.VIDEO);
        out = Assets.replaceAll(out,"@IMAGE", Lang.IMAGE);
        out = Assets.replaceAll(out,"@MUSIC", Lang.MUSIC);

        out = Assets.replaceAll(out,"@SEARCH", Lang.SEARCH);
        out = Assets.replaceAll(out,"@LOGIN", Lang.LOGIN);
        out = Assets.replaceAll(out,"@QUIT", Lang.QUIT);
        out = Assets.replaceAll(out,"@REGISTER", Lang.REGISTER);
        out = Assets.replaceAll(out,"@USERNAME", Lang.USERNAME);
        out = Assets.replaceAll(out,"@PASSWORD", Lang.PASSWORD);

        out = Assets.replaceAll(out,"@CLICK_TO_CLOSE", Lang.CLICK_TO_CLOSE);
        out = Assets.replaceAll(out,"@HINT_LIST_SEPARATOR", Lang.HINT_LIST_SEPARATOR);

        out = Assets.replaceAll(out,"@TITLE_NAME_SEARCH", Lang.TITLE_NAME_SEARCH);
        out = Assets.replaceAll(out,"@PLAYLIST_NAME_SEARCH", Lang.PLAYLIST_NAME_SEARCH);
        out = Assets.replaceAll(out,"@ARTIST_ACTOR_SEARCH", Lang.ARTIST_ACTOR_SEARCH);
        out = Assets.replaceAll(out,"@OWNER_SEARCH", Lang.OWNER_SEARCH);
        out = Assets.replaceAll(out,"@RELEASE_SEARCH", Lang.RELEASE_SEARCH);
        out = Assets.replaceAll(out,"@GENRE_SEARCH", Lang.GENRE_SEARCH);
        out = Assets.replaceAll(out,"@TAGS_SEARCH", Lang.TAGS_SEARCH);

        out = Assets.replaceAll(out,"@TITLE", Properties.TITLE);
        out = Assets.replaceAll(out,"@MOTTO", Properties.MOTTO[(int) ((Math.random() * 10) % Properties.MOTTO.length)]);

        out = out.replaceFirst("@CONTENT_TITLE", search);

        //

        return out;
    }

    /**
     * (General) Decrypt Language and Content tags and Return Data
     * @return
     */
    public String get(MediaEntity me, List<MediaEntity> playlist)
    {
        String out = data;
        String list = "";

        for (int i = 0; i < playlist.size() && i < 21; i++) list += "<a href=\"audio_" + playlist.get(i).getId() + "\"><li>" + playlist.get(i).getName() + "</li></a>";

        if(isPrep) out = Assets.replaceAll(prep,"@ELEMENTS", elements);

        out = Assets.replaceAll(out, "@PLAYLIST", list);

        out = Assets.replaceAll(out,"@TITLE", Properties.TITLE);
        out = Assets.replaceAll(out,"@MOTTO", Properties.MOTTO[(int) ((Math.random() * 10) % Properties.MOTTO.length)]);

        out = Assets.replaceAll(out,"@COVER_TN", me.getThumbnailName());
        out = Assets.replaceAll(out,"@COVER_TITLE", me.getName());
        out = Assets.replaceAll(out,"@DATA", me.getFileName());

        out = Assets.replaceAll(out,"@FILTER", Lang.FILTER);
        out = Assets.replaceAll(out,"@VIDEO", Lang.VIDEO);
        out = Assets.replaceAll(out,"@IMAGE", Lang.IMAGE);
        out = Assets.replaceAll(out,"@MUSIC", Lang.MUSIC);
        out = Assets.replaceAll(out,"@SEARCH", Lang.SEARCH);

        out = out.replaceFirst("@CONTENT_TITLE", (search == null ? "" : search));

        return out;
    }
}
