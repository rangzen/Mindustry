package io.anuke.mindustry.ui.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import io.anuke.mindustry.Vars;
import io.anuke.mindustry.ui.Links;
import io.anuke.mindustry.ui.Links.LinkEntry;
import io.anuke.ucore.scene.ui.ScrollPane;
import io.anuke.ucore.scene.ui.layout.Table;

public class AboutDialog extends FloatingDialog {

    public AboutDialog(){
        super("$text.about.button");

        addCloseButton();

        float h = 80f;
        float w = 600f;

        Table in = new Table();
        ScrollPane pane = new ScrollPane(in, "clear");

        for(LinkEntry link : Links.getLinks()){
            Table table = new Table("button");
            table.margin(0);
            table.table(img -> {
                img.addImage("white").height(h - 5).width(40f).color(link.color);
                img.row();
                img.addImage("white").height(5).width(40f).color(link.color.cpy().mul(0.8f, 0.8f, 0.8f, 1f));
            }).expandY();

            table.table(i -> {
                i.background("button");
                i.addImage("icon-" + link.name).size(14*3f);
            }).size(h-5, h);

            table.table(inset -> {
                inset.add("[accent]"+link.name.replace("-", " ")).growX().left();
                inset.row();
                inset.labelWrap(link.description).width(w - 100f).color(Color.LIGHT_GRAY).growX();
            }).padLeft(8);

            table.addImageButton("icon-link", 14*3, () -> {
                if(!Gdx.net.openURI(link.link)){
                    Vars.ui.showError("$text.linkfail");
                    Gdx.app.getClipboard().setContents(link.link);
                }
            }).size(h-5, h);

            in.add(table).size(w, h).padTop(5).row();
        }

        content().add(pane).growX();
    }
}
