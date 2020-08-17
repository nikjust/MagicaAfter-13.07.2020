// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class ModelVornskr extends EntityModel {
	private final ModelRenderer Head;
	private final ModelRenderer LegL;
	private final ModelRenderer LegR;
	private final ModelRenderer ArmL;
	private final ModelRenderer ArmR;
	private final ModelRenderer Body;
	private final ModelRenderer tail;
	private final ModelRenderer tail2;
	private final ModelRenderer tail3;
	private final ModelRenderer tail4;
	private final ModelRenderer Tail5;

	public ModelVornskr() {
		textureWidth = 64;
		textureHeight = 64;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 6.25F, -10.35F);
		Head.cubeList.add(new ModelBox(Head, 46, 30, -2.0F, 0.75F, -8.65F, 4, 3, 4, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 39, 6, -3.0F, -1.25F, -4.65F, 6, 5, 6, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 47, 1, 3.0F, -2.25F, 0.65F, 1, 1, 3, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 55, 1, -4.0F, -2.25F, 0.65F, 1, 1, 3, 0.0F, false));

		LegL = new ModelRenderer(this);
		LegL.setRotationPoint(4.0F, 13.5F, 10.0F);
		LegL.cubeList.add(new ModelBox(LegL, 22, 46, -1.0F, -0.5F, -1.0F, 2, 11, 2, 0.0F, false));

		LegR = new ModelRenderer(this);
		LegR.setRotationPoint(-4.0F, 13.5F, 10.0F);
		LegR.cubeList.add(new ModelBox(LegR, 22, 46, -1.0F, -0.5F, -1.0F, 2, 11, 2, 0.0F, false));

		ArmL = new ModelRenderer(this);
		ArmL.setRotationPoint(4.0F, 13.5F, -10.0F);
		ArmL.cubeList.add(new ModelBox(ArmL, 22, 46, -1.0F, -0.5F, -1.0F, 2, 11, 2, 0.0F, false));

		ArmR = new ModelRenderer(this);
		ArmR.setRotationPoint(-4.0F, 13.5F, -10.0F);
		ArmR.cubeList.add(new ModelBox(ArmR, 22, 46, -1.0F, -0.5F, -1.0F, 2, 11, 2, 0.0F, false));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 9.5F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 0, 0, -5.0F, -3.5F, -11.0F, 10, 7, 22, 0.0F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(-0.25F, 8.25F, 11.5F);
		tail.cubeList.add(new ModelBox(tail, 4, 38, -1.75F, -1.25F, -0.5F, 3, 3, 4, 0.0F, false));

		tail2 = new ModelRenderer(this);
		tail2.setRotationPoint(0.0F, 0.0F, 4.0F);
		tail.addChild(tail2);
		tail2.cubeList.add(new ModelBox(tail2, 4, 38, -1.75F, -1.25F, -0.5F, 3, 3, 4, 0.0F, false));

		tail3 = new ModelRenderer(this);
		tail3.setRotationPoint(0.0F, 0.25F, 4.0F);
		tail2.addChild(tail3);
		tail3.cubeList.add(new ModelBox(tail3, 18, 39, -0.75F, -0.5F, -0.5F, 1, 2, 4, 0.0F, false));

		tail4 = new ModelRenderer(this);
		tail4.setRotationPoint(0.0F, -0.05F, 4.0F);
		tail3.addChild(tail4);
		tail4.cubeList.add(new ModelBox(tail4, 28, 40, -0.75F, -0.45F, -0.5F, 1, 1, 4, 0.0F, false));

		Tail5 = new ModelRenderer(this);
		Tail5.setRotationPoint(-0.25F, 0.05F, 4.5F);
		tail4.addChild(Tail5);
		Tail5.cubeList.add(new ModelBox(Tail5, 37, 40, -0.5F, -0.5F, -1.0F, 1, 1, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Head.render(f5);
		LegL.render(f5);
		LegR.render(f5);
		ArmL.render(f5);
		ArmR.render(f5);
		Body.render(f5);
		tail.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.LegR.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.tail2.rotateAngleX = f3 / (180F / (float) Math.PI);
		this.tail3.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.tail4.rotateAngleY = f4 / (180F / (float) Math.PI);
		this.tail.rotateAngleY = f4 / (180F / (float) Math.PI);
		this.Tail5.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.ArmR.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.LegL.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.ArmL.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
	}
}