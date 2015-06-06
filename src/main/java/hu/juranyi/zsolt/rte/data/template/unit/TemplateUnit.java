package hu.juranyi.zsolt.rte.data.template.unit;

public abstract class TemplateUnit {
	private final String unitType = this.getClass().getSimpleName();

	public String getUnitType() {
		return unitType;
	}

	@Override
	public String toString() {
		return "TemplateUnit [unitType=" + unitType + "]";
	}

}
