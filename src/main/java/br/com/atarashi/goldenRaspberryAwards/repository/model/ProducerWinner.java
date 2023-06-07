package br.com.atarashi.goldenRaspberryAwards.repository.model;

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
@NoArgsConstructor
@AllArgsConstructor
public class ProducerWinner {
    private int year;
    private String producer;
    private boolean delete;
    private int interval;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("year", year)
                .append("producer", producer)
                .append("delete", delete)
                .append("interval", interval)
                .toString();
    }
}
