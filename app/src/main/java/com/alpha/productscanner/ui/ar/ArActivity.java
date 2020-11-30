package com.alpha.productscanner.ui.ar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.alpha.productscanner.R;
import com.alpha.productscanner.Singleton;
import com.alpha.productscanner.constant.AlphaConstant;
import com.alpha.productscanner.dto.Brand;
import com.alpha.productscanner.manager.navigation.NavigatorManager;
import com.alpha.productscanner.ui.list_products.ListProductFragment;
import com.alpha.productscanner.ui.main.MainActivity;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.Camera;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.collision.Ray;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.ExternalTexture;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ShapeFactory;
import com.google.ar.sceneform.ux.ArFragment;

import java.util.Collection;

public class ArActivity extends AppCompatActivity implements Scene.OnUpdateListener, Scene.OnPeekTouchListener, ArInterface.View {
    private CustomArFragment arFragment;
    private int brandToGet;
    private  boolean isModelPlaced = false;
    private ModelRenderable videoRenderable;
    private float HEIGHT = 0.50f;
    public boolean videoPlaying = false;
    Brand brandData;
    ArFragment arFragmentFind;
    ListProductFragment listFragment = new ListProductFragment();


    ArPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);
        presenter = new ArPresenter(this);
        arFragment = (CustomArFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentAr);
        arFragment.getArSceneView().getScene().addOnUpdateListener(this);
        arFragment.getArSceneView().getScene().addOnPeekTouchListener(this);




    }

    @Override
    public void onUpdate(FrameTime frameTime) {
                Frame frame =  arFragment.getArSceneView().getArFrame();
                Collection<AugmentedImage> images = frame.getUpdatedTrackables(AugmentedImage.class);
                for(AugmentedImage image :  images){
                    if(image.getTrackingState() == TrackingState.TRACKING){
                        if(image.getName().equals("gamesa")){
                            brandToGet = AlphaConstant.GAMESA;
                            retrieveBrandInformation(brandToGet,arFragment);
                        }else if(image.getName().equals("coca")){
                            brandToGet = AlphaConstant.COCA;
                            retrieveBrandInformation(brandToGet,arFragment);
                        }else if(image.getName().equals("sabritas")){

                            brandToGet = AlphaConstant.SABRITAS;
                            retrieveBrandInformation(brandToGet,arFragment);
                        }else if(image.getName().equals("bimbo")){
                            brandToGet = AlphaConstant.BIMBO;
                            retrieveBrandInformation(brandToGet,arFragment);
                        }else if(image.getName().equals("lala")){
                            brandToGet = AlphaConstant.LALA;
                            retrieveBrandInformation(brandToGet,arFragment);
                        }
                    }
                }
    }




    private void showVideo(int brandToGet) {
        ExternalTexture texture = new ExternalTexture();
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.myvideo);
        switch (brandToGet){
            case 1:
                mediaPlayer = MediaPlayer.create(this,R.raw.coca);
                break;
            case 2:
                mediaPlayer = MediaPlayer.create(this,R.raw.lala);
                break;
            case 3:
                mediaPlayer = MediaPlayer.create(this,R.raw.gamesa);
                break;
            case 4:
                mediaPlayer = MediaPlayer.create(this,R.raw.sabritas);
                break;
            case 5:
                mediaPlayer = MediaPlayer.create(this,R.raw.bimbo);
                break;
                default:

        }
        mediaPlayer.setSurface(texture.getSurface());
        mediaPlayer.setLooping(true);
        ModelRenderable
                .builder()
                .setSource(this,R.raw.video_screen)
                .build()
                .thenAccept(modelRenderable -> {
                    videoRenderable = modelRenderable;
                    videoRenderable.getMaterial().setExternalTexture("videoTexture", texture);
                    videoRenderable.getMaterial().setFloat4("keyColor", new Color(0.01843f,1.0f,0.98f));
                });




        Node videoNode = new Node();
        videoNode.setParent(arFragment.getArSceneView().getScene());
        videoNode.setRenderable(videoRenderable);
        arFragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
            Camera camera = arFragment.getArSceneView().getScene().getCamera();
            Ray ray = camera.screenPointToRay(1080/2f,1920/2f);
            Vector3 newPosition = ray.getPoint(1f);
            videoNode.setLocalPosition(newPosition);
        });
        float width = mediaPlayer.getVideoWidth();
        float height = mediaPlayer.getVideoHeight();
        videoNode.setLocalScale(new Vector3(
                HEIGHT * (width / height), HEIGHT, 1.0f
        ));


        if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();

            texture.getSurfaceTexture().setOnFrameAvailableListener(surfaceTexture -> {
                videoNode.setRenderable(videoRenderable);
                texture.getSurfaceTexture().setOnFrameAvailableListener(null);
            });
            videoPlaying = true;
        }else{
            videoNode.setRenderable(videoRenderable);
        }

    }

    private void retrieveBrandInformation(int brandToGet, ArFragment arFragment) {
        Log.d("OK-XX", "La marca que necesito:  " + brandToGet);
        //Provisional
        arFragmentFind=arFragment;

        presenter.getBrand(brandToGet);

/*
        brandData.setName("Grupo Gamesa S. de R.L. de C.V.");
        brandData.setImagename("imageName03");
        brandData.setAddres("Costa, Del Pacífico Sn, 71980 CDMX");
        brandData.setLatitude(19.35614f);
        brandData.setLongitude(-98.962105f);
        brandData.setUrlwebpage("https://www.gamesa.com.mx");
        brandData.setUrlfacebook("https://es-la.facebook.com/GamesaClasicasMX/");
        brandData.setUrlinstagram("https://www.instagram.com/explore/tags/gamesa/?hl=es-la");

        */
    }

    private void setUIMenu(ArFragment arFragment) {
        if(isModelPlaced)
            return;
         ModelRenderable.builder()
                .setSource(this, Uri.parse("model.sfb"))
                .build()
                .thenAccept(modelRenderable -> {
                    Node node = new Node();
                    node.setParent(arFragment.getArSceneView().getScene());
                    node.setRenderable(modelRenderable);
                    arFragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
                        Camera camera = arFragment.getArSceneView().getScene().getCamera();
                        Ray ray = camera.screenPointToRay(520f,580f);
                        Vector3 newPosition = ray.getPoint(1f);
                        node.setLocalPosition(newPosition);
                    });

                    node.setName("video");
                });
        //------------------------------------------------------------------------------------------
        MaterialFactory
                .makeOpaqueWithColor(this, new Color(android.graphics.Color.DKGRAY))
                .thenAccept(material -> {
                    ModelRenderable renderable = ShapeFactory.makeSphere(0.090f,
                            new Vector3(0f,-0.10f,0f),material);
                    Node node = new Node();
                    node.setParent(arFragment.getArSceneView().getScene());
                    node.setRenderable(renderable);
                    node.setName("Web");

                    arFragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
                        Camera camera = arFragment.getArSceneView().getScene().getCamera();
                        Ray ray = camera.screenPointToRay(1080/2f,1920/2f);
                        Vector3 newPosition = ray.getPoint(1f);
                        node.setLocalPosition(newPosition);
                    });


                });
        //------------------------------------------------------------------------------------------
        MaterialFactory
                .makeOpaqueWithColor(this, new Color(android.graphics.Color.BLUE))
                .thenAccept(material -> {
                    ModelRenderable renderable = ShapeFactory.makeSphere(0.090f,
                            new Vector3(0.25f,-0.25f,0f),material);

                    Node node = new Node();
                    node.setParent(arFragment.getArSceneView().getScene());
                    node.setRenderable(renderable);
                    node.setName("ProductList");
                    arFragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
                        Camera camera = arFragment.getArSceneView().getScene().getCamera();
                        Ray ray = camera.screenPointToRay(1080/2f,1920/2f);
                        Vector3 newPosition = ray.getPoint(1f);
                        node.setLocalPosition(newPosition);
                    });
                });
        //------------------------------------------------------------------------------------------
        MaterialFactory
                .makeOpaqueWithColor(this, new Color(android.graphics.Color.GREEN))
                .thenAccept(material -> {
                    ModelRenderable renderable = ShapeFactory.makeSphere(0.090f,
                            new Vector3(-0.25f,-0.25f,0f),material);
                    Node node = new Node();
                    node.setParent(arFragment.getArSceneView().getScene());
                    node.setRenderable(renderable);
                    node.setName("SingleProduct");
                    arFragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
                        Camera camera = arFragment.getArSceneView().getScene().getCamera();
                        Ray ray = camera.screenPointToRay(1080/2f,1920/2f);
                        Vector3 newPosition = ray.getPoint(1f);
                        node.setLocalPosition(newPosition);
                    });



                });
        //------------------------------------------------------------------------------------------
        MaterialFactory
                .makeOpaqueWithColor(this, new Color(android.graphics.Color.RED))
                .thenAccept(material -> {
                    ModelRenderable renderable = ShapeFactory.makeSphere(0.090f,
                            new Vector3(0f,-0.49f,0f),material);
                    Node node = new Node();
                    node.setParent(arFragment.getArSceneView().getScene());
                    node.setRenderable(renderable);
                    node.setName("Social");
                    arFragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
                        Camera camera = arFragment.getArSceneView().getScene().getCamera();
                        Ray ray = camera.screenPointToRay(1080/2f,1920/2f);
                        Vector3 newPosition = ray.getPoint(1f);
                        node.setLocalPosition(newPosition);
                    });
                });



    }






    public void setUpDatabase(Config config, Session session){
        Bitmap gamesaBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gamesa);
        Bitmap bimboBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.bimbo);
        Bitmap cocaBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.coca);
        Bitmap sabritasBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.sabritas);
        Bitmap lalaBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.lala);

        AugmentedImageDatabase aid = new AugmentedImageDatabase(session);
        aid.addImage("gamesa", gamesaBitmap);
        aid.addImage("bimbo", bimboBitmap);
        aid.addImage("coca", cocaBitmap);
        aid.addImage("lala", lalaBitmap);
        aid.addImage("sabritas", sabritasBitmap);
        config.setAugmentedImageDatabase(aid);
    }

    @Override
    public void onPeekTouch(HitTestResult hitTestResult, MotionEvent motionEvent) {
        Log.d("OK-XX", "Que manda: " + hitTestResult.getNode().getName());
        String option = hitTestResult.getNode().getName();
        switch (option){
            case "video":
                if(!videoPlaying){
                    Log.d("ElVideo", "LavideoPlaying" + videoPlaying);
                    hitTestResult.getNode().getRenderable().setShadowCaster(false);

                    showVideo(brandToGet);

                }
                break;
            case "Social":
                    String url = brandData.getUrlFacebook();
                    if(url != null && !url.isEmpty()){
                        Intent intenSocial = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intenSocial);
                    }else if(url.isEmpty()){
                        String urlInsta = brandData.getUrlInstagram();
                        if(urlInsta != null && !urlInsta.isEmpty()){
                            Intent intentSocial = new Intent(Intent.ACTION_VIEW, Uri.parse(urlInsta));
                            startActivity(intentSocial);
                        }
                    }
                break;
                case "Web":
                    String urlWeb = brandData.getUrlWebpage();
                    if(urlWeb != null && !urlWeb.isEmpty()){
                        Intent intenWeb = new Intent(Intent.ACTION_VIEW, Uri.parse(urlWeb));
                        startActivity(intenWeb);
                    }
                    break;
            case "SingleProduct":
                Log.d("OK-XX", "bra: "+brandToGet);
                break;
                case "ProductList":
                    Bundle bundle = new Bundle();
                    bundle.putInt(AlphaConstant.BUNDLE_ID_BRAND, brandToGet);
                   Intent mainIntent = new Intent(this, MainActivity.class);
                   mainIntent.putExtra("arListProduct", bundle);
                   startActivity(mainIntent);
                   this.finish();
                    break;

        }
    }

    @Override
    public void onSuccess(Brand brandData) {
        this.brandData=brandData;
        setUIMenu(arFragment);
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }
}
