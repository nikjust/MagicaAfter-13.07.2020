// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelsquid3 extends EntityModel {
	private final ModelRenderer body;
	private final ModelRenderer tentacle1;
	private final ModelRenderer tentacle2;
	private final ModelRenderer tentacle3;
	private final ModelRenderer tentacle4;
	private final ModelRenderer tentacle5;
	private final ModelRenderer tentacle6;
	private final ModelRenderer tentacle7;
	private final ModelRenderer tentacle8;

	public Modelsquid3() {
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -1.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -6.0F, -8.0F, -6.0F, 12, 16, 12, 0.0F, false));

		tentacle1 = new ModelRenderer(this);
		tentacle1.setRotationPoint(5.0F, 7.0F, 0.0F);
		body.addChild(tentacle1);
		setRotationAngle(tentacle1, 0.0F, 1.5708F, 0.0F);
		tentacle1.cubeList.add(new ModelBox(tentacle1, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));

		tentacle2 = new ModelRenderer(this);
		tentacle2.setRotationPoint(3.5F, 7.0F, 3.5F);
		body.addChild(tentacle2);
		setRotationAngle(tentacle2, 0.0F, 0.7854F, 0.0F);
		tentacle2.cubeList.add(new ModelBox(tentacle2, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));

		tentacle3 = new ModelRenderer(this);
		tentacle3.setRotationPoint(0.0F, 7.0F, 5.0F);
		body.addChild(tentacle3);
		tentacle3.cubeList.add(new ModelBox(tentacle3, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));

		tentacle4 = new ModelRenderer(this);
		tentacle4.setRotationPoint(-3.5F, 7.0F, 3.5F);
		body.addChild(tentacle4);
		setRotationAngle(tentacle4, 0.0F, -0.7854F, 0.0F);
		tentacle4.cubeList.add(new ModelBox(tentacle4, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));

		tentacle5 = new ModelRenderer(this);
		tentacle5.setRotationPoint(-5.0F, 7.0F, 0.0F);
		body.addChild(tentacle5);
		setRotationAngle(tentacle5, 0.0F, -1.5708F, 0.0F);
		tentacle5.cubeList.add(new ModelBox(tentacle5, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));

		tentacle6 = new ModelRenderer(this);
		tentacle6.setRotationPoint(-3.5F, 7.0F, -3.5F);
		body.addChild(tentacle6);
		setRotationAngle(tentacle6, 0.0F, -2.3562F, 0.0F);
		tentacle6.cubeList.add(new ModelBox(tentacle6, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));

		tentacle7 = new ModelRenderer(this);
		tentacle7.setRotationPoint(0.0F, 7.0F, -5.0F);
		body.addChild(tentacle7);
		setRotationAngle(tentacle7, 0.0F, -3.1416F, 0.0F);
		tentacle7.cubeList.add(new ModelBox(tentacle7, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));

		tentacle8 = new ModelRenderer(this);
		tentacle8.setRotationPoint(3.5F, 7.0F, -3.5F);
		body.addChild(tentacle8);
		setRotationAngle(tentacle8, 0.0F, -3.927F, 0.0F);
		tentacle8.cubeList.add(new ModelBox(tentacle8, 48, 0, -1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.tentacle1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.tentacle8.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.tentacle6.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.tentacle7.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.body.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.body.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.tentacle4.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.tentacle5.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.tentacle2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.tentacle3.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}