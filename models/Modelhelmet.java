// Made with Blockbench 3.6.2
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelhelmet extends EntityModel {
	private final ModelRenderer Helmet;
	private final ModelRenderer bone;
	private final ModelRenderer bone4;
	private final ModelRenderer bone6;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone5;

	public Modelhelmet() {
		textureWidth = 64;
		textureHeight = 64;

		Helmet = new ModelRenderer(this);
		Helmet.setRotationPoint(0.0F, 45.0F, 0.0F);
		Helmet.cubeList.add(new ModelBox(Helmet, 11, 42, -5.0F, -33.0F, -5.0F, 10, 1, 10, 0.0F, false));
		Helmet.cubeList.add(new ModelBox(Helmet, 36, 3, -5.0F, -32.0F, -5.0F, 10, 3, 1, 0.0F, false));
		Helmet.cubeList.add(new ModelBox(Helmet, 19, 25, 4.0F, -32.0F, -4.0F, 1, 8, 9, 0.0F, false));
		Helmet.cubeList.add(new ModelBox(Helmet, 19, 25, -5.0F, -32.0F, -4.0F, 1, 8, 9, 0.0F, false));
		Helmet.cubeList.add(new ModelBox(Helmet, 52, 7, 3.0F, -29.0F, -5.0F, 2, 2, 1, 0.0F, false));
		Helmet.cubeList.add(new ModelBox(Helmet, 52, 7, -5.0F, -29.0F, -5.0F, 2, 2, 1, 0.0F, false));
		Helmet.cubeList.add(new ModelBox(Helmet, 52, 7, -1.0F, -29.0F, -5.0F, 2, 2, 1, 0.0F, false));
		Helmet.cubeList.add(new ModelBox(Helmet, 45, 12, -4.0F, -34.0F, -1.0F, 2, 1, 2, 0.0F, false));
		Helmet.cubeList.add(new ModelBox(Helmet, 46, 13, 2.0F, -34.0F, -1.0F, 2, 1, 2, 0.0F, false));
		Helmet.cubeList.add(new ModelBox(Helmet, 0, 0, -4.0F, -32.0F, 4.0F, 8, 5, 1, 0.0F, false));
		Helmet.cubeList.add(new ModelBox(Helmet, 36, 3, -5.0F, -27.0F, -5.0F, 10, 3, 1, 0.0F, false));
		Helmet.cubeList.add(new ModelBox(Helmet, 2, 53, -5.0F, -27.0F, 4.0F, 10, 3, 1, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(2.9F, -34.3F, 0.0F);
		Helmet.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.3054F);
		bone.cubeList.add(new ModelBox(bone, 46, 13, -1.0F, -0.5F, -1.0F, 2, 1, 2, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0146F, -1.0439F, 0.0F);
		bone.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, -0.3054F);
		bone4.cubeList.add(new ModelBox(bone4, 46, 13, -1.0F, -0.4561F, -1.0F, 2, 1, 2, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.3277F, 0.2294F, 0.0F);
		bone4.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, -0.3054F);
		bone6.cubeList.add(new ModelBox(bone6, 46, 13, -1.0F, -1.4561F, -1.0F, 2, 1, 2, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-3.0F, -34.2F, 0.0F);
		Helmet.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.3054F);
		bone2.cubeList.add(new ModelBox(bone2, 46, 13, -1.0F, -0.6F, -1.0F, 2, 1, 2, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-0.0146F, -1.0439F, 0.0F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, 0.3054F);
		bone3.cubeList.add(new ModelBox(bone3, 45, 13, -1.0F, -0.5561F, -1.0F, 2, 1, 2, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-0.1638F, 0.1147F, 0.0F);
		bone3.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 0.0F, 0.3054F);
		bone5.cubeList.add(new ModelBox(bone5, 45, 13, -1.0F, -1.5561F, -1.0F, 2, 1, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Helmet.render(f5);
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