package br.com.atarashi.goldenRaspberryAwards.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "\"FILM\"")
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "\"year\"")
    private String year;
    @Column(name = "title")
    private String title;
    @Column(name = "studios")
    private String studios;
    @Column(name = "producers")
    private String producers;
    @Column(name = "winner")
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
