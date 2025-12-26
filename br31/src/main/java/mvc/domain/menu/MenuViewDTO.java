package mvc.domain.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuViewDTO {
	private int products_id;
	private int category_id;
	private String product_name;
	private String english_name;
	private String description;
	private String poster_path;
	private IceNutritionDTO iceNutritionDTO;
	
}
