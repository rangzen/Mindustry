package io.anuke.mindustry.ui;

import io.anuke.ucore.function.Listenable;
import io.anuke.ucore.scene.ui.TextButton;

public class MenuButton extends TextButton{
	
	public MenuButton(String icon, String text, Listenable clicked){
		super("default");
		float s = 70f;

		clicked(clicked);

		clearChildren();

		margin(0);

		table(t -> {
			t.addImage(icon).size(14*3);
			t.update(() -> t.setBackground(getClickListener().isOver() || getClickListener().isVisualPressed() ? "button-over" : "button"));
		}).size(s - 5, s);

		add(text).padLeft(5).growX();
	}
}
