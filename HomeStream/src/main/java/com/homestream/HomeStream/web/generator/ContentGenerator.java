package com.homestream.HomeStream.web.generator;

import com.homestream.HomeStream.entity.*;

import java.util.List;

public class ContentGenerator
{
    /**
     * Function to create Content Row for dynamic JavaScript Request
     * @param media
     * @return
     */
    public String generate(List<? extends IEntity> media, int startIndex)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("</div><div class=\"list BOTTOM_SMAL\">\n");

        if(startIndex > media.size()) return null;
        for (int i = startIndex, j = 0; i < media.size() && j < 5; i++, j++)
        {

            builder.append(
                    "<div class=\"cover\">\n" +
                    "<img name=\"cover\" src=\"data/" + ((MediaEntity) media.get(i)).getThumbnailName() + "\">\n" +
                    "<div name=\"coverText\">\n" +
                    "<h2 name=\"coverText\">" + ((MediaEntity) media.get(i)).getName() + "</h2>\n");

            if(media.get(i).getClass().toString().contains("MusicEntity")) builder.append(
                    "<h2><a><i name=\"music\" id=\"" + ((MusicEntity) media.get(i)).getFileName() + "\" title=\"" + ((MusicEntity) media.get(i)).getName() + "\" class=\"fa fa-fw fa-play mediaButton\" id=\"user\"></i></a>\t<a data=\"" + ((MusicEntity) media.get(i)).getId() + "\"><i class=\"fa fa-fw fa-info mediaButton\" id=\"user\"></i></a></h2>\n");
            else if(media.get(i).getClass().toString().contains("FilmEntity")) builder.append(
                    "<h2><a><i name=\"video\" id=\"" + ((FilmEntity) media.get(i)).getFileName() + "\" title=\"" + ((FilmEntity) media.get(i)).getName() + "\" class=\"fa fa-fw fa-play mediaButton\" id=\"user\"></i></a>\t<a data=\"" + ((FilmEntity) media.get(i)).getId() + "\"><i class=\"fa fa-fw fa-info mediaButton\" id=\"user\"></i></a></h2>\n");
            else if(media.get(i).getClass().toString().contains("ImageEntity")) builder.append(
                    "<h2><a><i name=\"video\" id=\"" + ((ImageEntity) media.get(i)).getFileName() + "\" title=\"" + ((ImageEntity) media.get(i)).getName() + "\" class=\"fa fa-fw fa-play mediaButton\" id=\"user\"></i></a>\t<a data=\"" + ((ImageEntity) media.get(i)).getId() + "\"><i class=\"fa fa-fw fa-info mediaButton\" id=\"user\"></i></a></h2>\n");
            else; // Playlist
        }

        builder.append("</div>\n</div>\n");

        return builder.toString();
    }
}
