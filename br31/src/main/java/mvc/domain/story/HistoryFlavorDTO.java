package mvc.domain.story;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class HistoryFlavorDTO {
	public int history_ice_id;
	public int history_year;
	public int history_month;
	public String history_ice_name;
	public String history_img_path;
}
