// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelquadrocopter extends EntityModel {
	private final ModelRenderer all;
	private final ModelRenderer heli4;
	private final ModelRenderer heli3;
	private final ModelRenderer heli2;
	private final ModelRenderer heli1;
	private final ModelRenderer bone;

	public Modelquadrocopter() {
		textureWidth = 16;
		textureHeight = 16;

		all = new ModelRenderer(this);
		all.setRotationPoint(-4.5F, 22.2F, -4.5F);

		heli4 = new ModelRenderer(this);
		heli4.setRotationPoint(9.0F, 0.0F, 9.0F);
		all.addChild(heli4);
		heli4.cubeList.add(new ModelBox(heli4, 0, 0, -2.5F, -0.2F, -0.5F, 5, 1, 1, 0.0F, false));
		heli4.cubeList.add(new ModelBox(heli4, 0, 0, -0.5F, -0.2F, -2.5F, 1, 1, 5, 0.0F, false));
		heli4.cubeList.add(new ModelBox(heli4, 0, 0, -6.0F, 1.6F, -7.0F, 3, 2, 2, 0.0F, false));

		heli3 = new ModelRenderer(this);
		heli3.setRotationPoint(9.0F, 0.0F, 0.0F);
		all.addChild(heli3);
		heli3.cubeList.add(new ModelBox(heli3, 0, 0, -2.5F, -0.2F, -0.5F, 5, 1, 1, 0.0F, false));
		heli3.cubeList.add(new ModelBox(heli3, 0, 0, -0.5F, -0.2F, -2.5F, 1, 1, 5, 0.0F, false));

		heli2 = new ModelRenderer(this);
		heli2.setRotationPoint(0.0F, 0.0F, 0.0F);
		all.addChild(heli2);
		heli2.cubeList.add(new ModelBox(heli2, 0, 0, -2.5F, -0.2F, -0.5F, 5, 1, 1, 0.0F, false));
		heli2.cubeList.add(new ModelBox(heli2, 0, 0, -0.5F, -0.2F, -2.5F, 1, 1, 5, 0.0F, false));

		heli1 = new ModelRenderer(this);
		heli1.setRotationPoint(0.0F, 0.0F, 9.0F);
		all.addChild(heli1);
		heli1.cubeList.add(new ModelBox(heli1, 0, 0, -2.5F, -0.2F, -0.5F, 5, 1, 1, 0.0F, false));
		heli1.cubeList.add(new ModelBox(heli1, 0, 0, -0.5F, -0.2F, -2.5F, 1, 1, 5, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(4.5F, 1.8F, 4.5F);
		all.addChild(bone);
		setRotationAngle(bone, 0.0F, -0.7854F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -7.0F, -1.0F, -1.0F, 14, 1, 2, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.0F, -1.0F, -7.0F, 2, 1, 14, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -1.0F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		all.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.all.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.all.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.heli3.rotateAngleY = f2;
		this.heli2.rotateAngleY = f2;
		this.heli4.rotateAngleY = f2;
		this.heli1.rotateAngleY = f2;
	}
}