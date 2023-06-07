package br.com.atarashi.goldenRaspberryAwards.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Setter
@Builder(toBuilder = true)
public class FilmResponse {

    private int id;
    private String year;
    private String title;
    private String studios;
    private String producers;
    private String winner;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("id", id)
                .append("year", year)
                .append("title", title)
                .append("studios", studios)
                .append("producers", producers)
                .append("winner", winner)
                .toString();
    }
}
