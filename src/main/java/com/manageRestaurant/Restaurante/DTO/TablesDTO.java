package com.manageRestaurant.Restaurante.DTO;

import com.manageRestaurant.Restaurante.models.TablesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TablesDTO {
    private Long id;
    private Integer number;
    private Integer capacity;
    private String observation;

    public TablesModel toTablesModel() {
        TablesModel tablesModel = new TablesModel();
        tablesModel.setId(this.id);
        tablesModel.setNumber(this.number);
        tablesModel.setCapacity(this.capacity);
        tablesModel.setObservation(this.observation);
        return tablesModel;
    }

    public TablesModel getTablesModel() {
        TablesModel tablesModel = new TablesModel();
        tablesModel.setId(this.id);
        return tablesModel;
    }
}
