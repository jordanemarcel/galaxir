package fr.umlv.ir2.galaxir;

public interface ClickableItem extends GalaxyItem {
	public abstract void selected(Player player);
	public abstract void unselected(Player player);
	public abstract void moveShipTowards(Planet p, int percentage);
	public abstract void selectAndAdd(Player player);
	public abstract void unselectAndRemove(Player player);
}
