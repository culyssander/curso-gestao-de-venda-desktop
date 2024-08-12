
package com.culysoft.gestao.venda.visao.modelo;

import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuModelo {
    private String icon;
    private String nome;
    private TipoMenu tipoMenu;
    
    public enum TipoMenu {
        MENU, TITULO, VAZIO;
    }
    
    public Icon toIcon() {
        String filename = System.getProperty("user.dir") + "\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\" + icon + ".png";
        return new ImageIcon(filename);
    }
    
}
