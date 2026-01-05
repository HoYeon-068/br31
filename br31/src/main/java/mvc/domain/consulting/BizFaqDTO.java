package mvc.domain.consulting;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BizFaqDTO {
	private int biz_faq_id;
	private String question;
	private String answer;
	private Date reg_date;
}
