package mvc.domain.menu;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuListDTO {
	private int products_id;
	private int category_id;
	private String product_name;
	private String category_name;
	private String tags;
	private String sub_title;
	private String img_path;
	private String bg_color;
	private String span_color;
}
