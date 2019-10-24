package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class MyGdxGame implements ApplicationListener {
	public PerspectiveCamera gameCam;
	public PerspectiveCamera guiCam;
	public CameraInputController camController;
	public Shader shader;
	public Model model;
	public ModelBuilder modelBuilder;
	public AssetManager assets;
	public Array<ModelInstance> instances = new Array<ModelInstance>();
	public ModelBatch modelBatch;
	public Batch batch;
	public Environment environment;
	public BitmapFont font;
	public BodyPart bodyPart;
	public boolean loading;

	@Override
	public void create(){
		modelBatch = new ModelBatch();
		modelBuilder = new ModelBuilder();
		bodyPart = new BodyPart();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		gameCam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		gameCam.position.set(1f, 1f, 20f);
		gameCam.lookAt(0,0,0);
		gameCam.near = 1f;
		gameCam.far = 300f;
		gameCam.update();

		guiCam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		guiCam.position.set(1f, 1f, 20f);
		guiCam.lookAt(0, 0, 0);
		guiCam.near = 1f;
		guiCam.far = 300f;
		guiCam.update();

		camController = new CameraInputController(gameCam);
		Gdx.input.setInputProcessor(camController);

		//Todo Make factory =)
		ModelInstance bodyBot = bodyPart.create(0f, 0,0,0.5f, Vector3.Z, 0, model);
		instances.add(bodyBot);
		ModelInstance bodyTop = bodyPart.create(0, 7.5f,0,0.5f, Vector3.Z, 0, model);
		instances.add(bodyTop);
		ModelInstance shoulderLeft = bodyPart.create(2, 7.5f,0,0.5f, Vector3.Z, 0, model);
		instances.add(shoulderLeft);
		ModelInstance shoulderRight = bodyPart.create(-2, 7.5f,0,0.5f, Vector3.Z, 0, model);
		instances.add(shoulderRight);
		ModelInstance neck = bodyPart.create(0, 8.5f,0,0.5f, Vector3.Z, 0, model);
		instances.add(neck);
		ModelInstance elbowLeft = bodyPart.create(4, 4,0,0.5f, Vector3.Z, 0, model);
		instances.add(elbowLeft);
		ModelInstance elbowRight = bodyPart.create(-4, 4,0,0.5f, Vector3.Z, 0, model);
		instances.add(elbowRight);
		ModelInstance wristLeft = bodyPart.create(4, -1,0,0.5f, Vector3.Z, 0, model);
		instances.add(wristLeft);
		ModelInstance wristRight = bodyPart.create(-4, -1,0,0.5f, Vector3.Z, 0, model);
		instances.add(wristRight);
		ModelInstance kneeLeft = bodyPart.create(2, -6,0,0.5f, Vector3.Z, 0, model);
		instances.add(kneeLeft);
		ModelInstance kneeRight = bodyPart.create(-2, -6,0,0.5f, Vector3.Z, 0, model);
		instances.add(kneeRight);
		ModelInstance ankleLeft = bodyPart.create(2, -12,0,0.5f, Vector3.Z, 0, model);
		instances.add(ankleLeft);
		ModelInstance ankleRight = bodyPart.create(-2, -12,0,0.5f, Vector3.Z, 0, model);
		instances.add(ankleRight);


		assets = new AssetManager();
		assets.load("data/human.obj", Model.class);
		loading = true;
		shader = new TestShader();
		shader.init();

		modelBatch = new ModelBatch();
		batch = new SpriteBatch();
		batch.setProjectionMatrix(guiCam.combined);

		font = new BitmapFont();
		font.setColor(Color.RED);
	}

	private void doneLoading(){
		Model human = assets.get("data/human.obj", Model.class);
		ModelInstance humanInstance = new ModelInstance(human);
		ColorAttribute attributeU = new TestShader.TestColorAttribute(TestShader.TestColorAttribute.DiffuseU, 1f, 0f, 0f, 1);
		humanInstance.materials.get(0).set(attributeU);
		ColorAttribute attributeV = new TestShader.TestColorAttribute(TestShader.TestColorAttribute.DiffuseV, 1f, 0f, 0f, 1);
		humanInstance.materials.get(0).set(attributeV);
		humanInstance.transform.setToRotation(Vector3.Z,90);
		humanInstance.transform.setTranslation(10,0, 0);
//		instances.add(humanInstance);
		loading = false;
	}

	@Override
	public void render(){
		if (loading && assets.update()) {
			doneLoading();
		}
		camController.update();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(gameCam);
		modelBatch.render(instances, environment);
		modelBatch.end();

		batch.begin();
		// Render GUI items
		HelperClass.DrawDebugLine(new Vector2(0,-200), new Vector2(0,200), 4, Color.RED, guiCam.combined);
		HelperClass.DrawDebugLine(new Vector2(-200,0), new Vector2(200,0), 4, Color.BLUE, guiCam.combined);
		font.draw(batch, "debug line here", 200, 200);
		batch.end();
	}

	@Override
	public void dispose(){
		shader.dispose();
		modelBatch.dispose();
		batch.dispose();
		instances.clear();
		assets.dispose();
		font.dispose();
	}

	@Override public void resize(int width, int height){}
	@Override public void pause(){}
	@Override public void resume(){}
}