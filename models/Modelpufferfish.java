// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

public static class Modelpufferfish extends EntityModel {
	private final ModelRenderer body_large;
	private final ModelRenderer leftFin;
	private final ModelRenderer rightFin;
	private final ModelRenderer spines_top_front;
	private final ModelRenderer spines_top_mid;
	private final ModelRenderer spines_top_back;
	private final ModelRenderer spines_bottom_front;
	private final ModelRenderer spines_bottom_mid;
	private final ModelRenderer spines_bottom_back;
	private final ModelRenderer spines_left_front;
	private final ModelRenderer spines_left_mid;
	private final ModelRenderer spines_left_back;
	private final ModelRenderer spines_right_front;
	private final ModelRenderer spines_right_mid;
	private final ModelRenderer spines_right_back;

	public Modelpufferfish() {
		textureWidth = 32;
		textureHeight = 32;

		body_large = new ModelRenderer(this);
		body_large.setRotationPoint(0.0F, 24.0F, 0.0F);
		body_large.cubeList.add(new ModelBox(body_large, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));

		leftFin = new ModelRenderer(this);
		leftFin.setRotationPoint(4.0F, -7.0F, 3.0F);
		body_large.addChild(leftFin);
		leftFin.cubeList.add(new ModelBox(leftFin, 24, 3, 0.0F, 0.0F, -5.9904F, 2, 1, 2, 0.0F, false));

		rightFin = new ModelRenderer(this);
		rightFin.setRotationPoint(-4.0F, -7.0F, 1.0F);
		body_large.addChild(rightFin);
		rightFin.cubeList.add(new ModelBox(rightFin, 24, 0, -1.9968F, 0.0F, -3.992F, 2, 1, 2, 0.0F, false));

		spines_top_front = new ModelRenderer(this);
		spines_top_front.setRotationPoint(-4.0F, -8.0F, -4.0F);
		body_large.addChild(spines_top_front);
		setRotationAngle(spines_top_front, 0.7854F, 0.0F, 0.0F);
		spines_top_front.cubeList.add(new ModelBox(spines_top_front, 14, 16, 0.0F, -1.0F, 0.0F, 8, 1, 1, 0.0F, false));

		spines_top_mid = new ModelRenderer(this);
		spines_top_mid.setRotationPoint(0.0F, -8.0F, 0.0F);
		body_large.addChild(spines_top_mid);
		spines_top_mid.cubeList.add(new ModelBox(spines_top_mid, 14, 16, -4.0F, -1.0F, 0.0F, 8, 1, 1, 0.0F, false));

		spines_top_back = new ModelRenderer(this);
		spines_top_back.setRotationPoint(0.0F, -8.0F, 4.0F);
		body_large.addChild(spines_top_back);
		setRotationAngle(spines_top_back, -0.7854F, 0.0F, 0.0F);
		spines_top_back.cubeList.add(new ModelBox(spines_top_back, 14, 16, -4.0F, -1.0F, 0.0F, 8, 1, 1, 0.0F, false));

		spines_bottom_front = new ModelRenderer(this);
		spines_bottom_front.setRotationPoint(0.0F, 0.0F, -4.0F);
		body_large.addChild(spines_bottom_front);
		setRotationAngle(spines_bottom_front, -0.7854F, 0.0F, 0.0F);
		spines_bottom_front.cubeList
				.add(new ModelBox(spines_bottom_front, 14, 19, -4.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F, false));

		spines_bottom_mid = new ModelRenderer(this);
		spines_bottom_mid.setRotationPoint(0.0F, 1.0F, 0.0F);
		body_large.addChild(spines_bottom_mid);
		spines_bottom_mid.cubeList
				.add(new ModelBox(spines_bottom_mid, 14, 19, -4.0F, -1.0F, 0.0F, 8, 1, 1, 0.0F, false));

		spines_bottom_back = new ModelRenderer(this);
		spines_bottom_back.setRotationPoint(0.0F, 0.0F, 4.0F);
		body_large.addChild(spines_bottom_back);
		setRotationAngle(spines_bottom_back, 0.7854F, 0.0F, 0.0F);
		spines_bottom_back.cubeList
				.add(new ModelBox(spines_bottom_back, 14, 19, -4.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F, false));

		spines_left_front = new ModelRenderer(this);
		spines_left_front.setRotationPoint(4.0F, 0.0F, -4.0F);
		body_large.addChild(spines_left_front);
		setRotationAngle(spines_left_front, 0.0F, 0.7854F, 0.0F);
		spines_left_front.cubeList.add(new ModelBox(spines_left_front, 0, 16, 0.0F, -8.0F, 0.0F, 1, 8, 1, 0.0F, false));

		spines_left_mid = new ModelRenderer(this);
		spines_left_mid.setRotationPoint(4.0F, 0.0F, 0.0F);
		body_large.addChild(spines_left_mid);
		spines_left_mid.cubeList.add(new ModelBox(spines_left_mid, 4, 16, 0.0F, -8.0F, 0.0F, 1, 8, 1, 0.0F, true));

		spines_left_back = new ModelRenderer(this);
		spines_left_back.setRotationPoint(4.0F, 0.0F, 4.0F);
		body_large.addChild(spines_left_back);
		setRotationAngle(spines_left_back, 0.0F, -0.7854F, 0.0F);
		spines_left_back.cubeList.add(new ModelBox(spines_left_back, 8, 16, 0.0F, -8.0F, 0.0F, 1, 8, 1, 0.0F, true));

		spines_right_front = new ModelRenderer(this);
		spines_right_front.setRotationPoint(-4.0F, 0.0F, -4.0F);
		body_large.addChild(spines_right_front);
		setRotationAngle(spines_right_front, 0.0F, -0.7854F, 0.0F);
		spines_right_front.cubeList
				.add(new ModelBox(spines_right_front, 4, 16, -1.0F, -8.0F, 0.0F, 1, 8, 1, 0.0F, false));

		spines_right_mid = new ModelRenderer(this);
		spines_right_mid.setRotationPoint(-4.0F, 0.0F, 0.0F);
		body_large.addChild(spines_right_mid);
		spines_right_mid.cubeList.add(new ModelBox(spines_right_mid, 8, 16, -1.0F, -8.0F, 0.0F, 1, 8, 1, 0.0F, false));

		spines_right_back = new ModelRenderer(this);
		spines_right_back.setRotationPoint(-4.0F, 0.0F, 4.0F);
		body_large.addChild(spines_right_back);
		setRotationAngle(spines_right_back, 0.0F, 0.7854F, 0.0F);
		spines_right_back.cubeList
				.add(new ModelBox(spines_right_back, 8, 16, -1.0F, -8.0F, 0.0F, 1, 8, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body_large.render(f5);
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