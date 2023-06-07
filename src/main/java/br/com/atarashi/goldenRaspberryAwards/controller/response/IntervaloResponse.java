package br.com.atarashi.goldenRaspberryAwards.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class IntervaloResponse {

    private String producer;
    private String interval;
    private String previousWin;
    private String followingWin;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("producer", producer)
                .append("interval", interval)
                .append("previousWin", previousWin)
                .append("followingWin", followingWin)
                .toString();
    }
}
