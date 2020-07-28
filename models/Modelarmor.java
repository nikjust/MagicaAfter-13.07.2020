// Made with Blockbench 3.6.2
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelarmor extends EntityModel {
	private final ModelRenderer armor;
	private final ModelRenderer armr;
	private final ModelRenderer arml;

	public Modelarmor() {
		textureWidth = 64;
		textureHeight = 64;

		armor = new ModelRenderer(this);
		armor.setRotationPoint(0.0F, 25.0F, 0.0F);
		armor.cubeList.add(new ModelBox(armor, 0, 0, -4.0F, -25.0F, 2.0F, 8, 12, 1, 0.0F, false));
		armor.cubeList.add(new ModelBox(armor, 0, 0, -4.0F, -25.0F, -3.0F, 8, 12, 1, 0.0F, false));

		armr = new ModelRenderer(this);
		armr.setRotationPoint(-4.0F, -30.0F, -3.0F);
		armor.addChild(armr);
		armr.cubeList.add(new ModelBox(armr, 0, 0, -5.0F, 5.0F, 0.0F, 1, 6, 6, 0.0F, false));
		armr.cubeList.add(new ModelBox(armr, 0, 0, -4.0F, 4.0F, 0.0F, 5, 1, 6, 0.0F, false));
		armr.cubeList.add(new ModelBox(armr, 0, 0, -4.0F, 5.0F, 5.0F, 4, 6, 1, 0.0F, false));
		armr.cubeList.add(new ModelBox(armr, 0, 0, -4.0F, 5.0F, 0.0F, 4, 6, 1, 0.0F, false));

		arml = new ModelRenderer(this);
		arml.setRotationPoint(-4.0F, -30.0F, -3.0F);
		armor.addChild(arml);
		arml.cubeList.add(new ModelBox(arml, 0, 0, 12.0F, 5.0F, 0.0F, 1, 6, 6, 0.0F, false));
		arml.cubeList.add(new ModelBox(arml, 0, 0, 8.0F, 4.0F, 0.0F, 5, 1, 6, 0.0F, false));
		arml.cubeList.add(new ModelBox(arml, 0, 0, 8.0F, 5.0F, 0.0F, 4, 6, 1, 0.0F, false));
		arml.cubeList.add(new ModelBox(arml, 0, 0, 8.0F, 5.0F, 5.0F, 4, 6, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		armor.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}