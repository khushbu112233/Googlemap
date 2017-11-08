package googlemap.com.googlemap;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static googlemap.com.googlemap.R.id.map;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback{

    // Google Map

    MarkerOptions marker;
    private GoogleMap googleMap;
    HashMap<Marker, MapDetail> eventMarkerMap;
    ArrayList<MapDetail> map_details= new ArrayList<MapDetail>();
    public String str = "{\n" +
            "  \"error\": false,\n" +
            "  \"success\": true,\n" +
            "  \"errors\": [\n" +
            "    \n" +
            "  ],\n" +
            "  \"response\": {\n" +
            "    \"beams\": [\n" +
            "      {\n" +
            "        \"id\": \"5947\",\n" +
            "        \"subject\": \"1904 North Western Ave\",\n" +
            "        \"type\": \"offer\",\n" +
            "        \"creation\": \"1472727043\",\n" +
            "        \"creator\": \"5970\",\n" +
            "        \"in_icebox\": 0,\n" +
            "        \"creator_name\": \"Animale Chicago\",\n" +
            "        \"expiration\": \"1472813443\",\n" +
            "        \"validity\": \"1472813443\",\n" +
            "        \"displayed\": \"0\",\n" +
            "        \"viewed\": \"0\",\n" +
            "        \"message\": \"Approved by Cameron at Animale\",\n" +
            "        \"quantity\": \"20\",\n" +
            "        \"original\": \"15\",\n" +
            "        \"value\": \"15\",\n" +
            "        \"discount\": \"0\",\n" +
            "        \"category_id\": \"3\",\n" +
            "        \"icebox_setting\": \"90\",\n" +
            "        \"beam_eligible\": \"\",\n" +
            "        \"beam_beacon_message\": \"A new, authentic causal Italian restaurant.\",\n" +
            "        \"expiration_array\": {\n" +
            "          \"day\": 0,\n" +
            "          \"hour\": 21,\n" +
            "          \"minute\": 4\n" +
            "        },\n" +
            "        \"business_facebook_url\": \"https:\\/\\/www.facebook.com\\/animalechicago\",\n" +
            "        \"twitter_handle\": \"@animalechicago\",\n" +
            "        \"business_yelp_id\": \"https:\\/\\/www.yelp.com\\/biz\\/animale-chicago\",\n" +
            "        \"business_default_color\": \"#000000\",\n" +
            "        \"website\": \"www.animalechicago.com\",\n" +
            "        \"sold_out\": \"0\",\n" +
            "        \"latitude\": 41.916,\n" +
            "        \"longitude\": -87.6876,\n" +
            "        \"expired\": \"0\",\n" +
            "        \"subscriber_only\": \"1\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"5946\",\n" +
            "        \"subject\": \"3301 N Clark St, Chicago, IL 60657\",\n" +
            "        \"type\": \"offer\",\n" +
            "        \"creation\": \"1472726958\",\n" +
            "        \"creator\": \"5549\",\n" +
            "        \"in_icebox\": 0,\n" +
            "        \"creator_name\": \"Hutch Chicago\",\n" +
            "        \"expiration\": \"1472813358\",\n" +
            "        \"validity\": \"1472813358\",\n" +
            "        \"displayed\": \"0\",\n" +
            "        \"viewed\": \"0\",\n" +
            "        \"message\": \"Approved by Marc\",\n" +
            "        \"quantity\": \"20\",\n" +
            "        \"original\": \"15\",\n" +
            "        \"value\": \"15\",\n" +
            "        \"discount\": \"0\",\n" +
            "        \"category_id\": \"3\",\n" +
            "        \"icebox_setting\": \"90\",\n" +
            "        \"beam_eligible\": \"\",\n" +
            "        \"beam_beacon_message\": \"\",\n" +
            "        \"expiration_array\": {\n" +
            "          \"day\": 0,\n" +
            "          \"hour\": 21,\n" +
            "          \"minute\": 2\n" +
            "        },\n" +
            "        \"business_facebook_url\": \"https:\\/\\/www.facebook.com\\/hutchchicago\\/\",\n" +
            "        \"twitter_handle\": \"@HutchChicago\",\n" +
            "        \"business_yelp_id\": \"https:\\/\\/www.yelp.com\\/biz\\/hutch-chicago\",\n" +
            "        \"business_default_color\": \"#000000\",\n" +
            "        \"website\": \"www.hutchchicago.com\",\n" +
            "        \"sold_out\": \"0\",\n" +
            "        \"latitude\": 41.9419,\n" +
            "        \"longitude\": -87.6521,\n" +
            "        \"expired\": \"0\",\n" +
            "        \"subscriber_only\": \"0\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"5945\",\n" +
            "        \"subject\": \"415 North Milwaukee Avenue\",\n" +
            "        \"type\": \"offer\",\n" +
            "        \"creation\": \"1472726876\",\n" +
            "        \"creator\": \"5369\",\n" +
            "        \"in_icebox\": 0,\n" +
            "        \"creator_name\": \"Paramount Room\",\n" +
            "        \"expiration\": \"1472813276\",\n" +
            "        \"validity\": \"1472813276\",\n" +
            "        \"displayed\": \"0\",\n" +
            "        \"viewed\": \"0\",\n" +
            "        \"message\": \"Approved by Kathleen\",\n" +
            "        \"quantity\": \"30\",\n" +
            "        \"original\": \"10\",\n" +
            "        \"value\": \"10\",\n" +
            "        \"discount\": \"0\",\n" +
            "        \"category_id\": \"3\",\n" +
            "        \"icebox_setting\": \"90\",\n" +
            "        \"beam_eligible\": \"\",\n" +
            "        \"beam_beacon_message\": \"An acclaimed modern food & drink spot in a 100 year old former speakeasy.\",\n" +
            "        \"expiration_array\": {\n" +
            "          \"day\": 0,\n" +
            "          \"hour\": 21,\n" +
            "          \"minute\": 1\n" +
            "        },\n" +
            "        \"business_facebook_url\": \"https:\\/\\/www.facebook.com\\/ParamountRoom\",\n" +
            "        \"twitter_handle\": \"@paramountroom\",\n" +
            "        \"business_yelp_id\": \"https:\\/\\/www.yelp.com\\/biz\\/paramount-room-chicago\",\n" +
            "        \"business_default_color\": \"#000000\",\n" +
            "        \"website\": \"http:\\/\\/paramountroom.com\\/\",\n" +
            "        \"sold_out\": \"0\",\n" +
            "        \"latitude\": 41.8896,\n" +
            "        \"longitude\": -87.6449,\n" +
            "        \"expired\": \"0\",\n" +
            "        \"subscriber_only\": \"0\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": \"5944\",\n" +
            "        \"subject\": \"1512 N Naper Blvd #152, Naperville, IL 60563\",\n" +
            "        \"type\": \"offer\",\n" +
            "        \"creation\": \"1472726791\",\n" +
            "        \"creator\": \"5323\",\n" +
            "        \"in_icebox\": 0,\n" +
            "        \"creator_name\": \"Schmaltz Deli\",\n" +
            "        \"expiration\": \"1472813191\",\n" +
            "        \"validity\": \"1472813191\",\n" +
            "        \"displayed\": \"0\",\n" +
            "        \"viewed\": \"0\",\n" +
            "        \"message\": \"Approved by Kristen at Schmaltz Deli\",\n" +
            "        \"quantity\": \"100\",\n" +
            "        \"original\": \"10\",\n" +
            "        \"value\": \"10\",\n" +
            "        \"discount\": \"0\",\n" +
            "        \"category_id\": \"1\",\n" +
            "        \"icebox_setting\": \"90\",\n" +
            "        \"beam_eligible\": \"\",\n" +
            "        \"beam_beacon_message\": \"\",\n" +
            "        \"expiration_array\": {\n" +
            "          \"day\": 0,\n" +
            "          \"hour\": 20,\n" +
            "          \"minute\": 59\n" +
            "        },\n" +
            "        \"business_facebook_url\": \"https:\\/\\/www.facebook.com\\/schmaltzdeli\\/\",\n" +
            "        \"twitter_handle\": \"@schmaltzdeli   \",\n" +
            "        \"business_yelp_id\": \"https:\\/\\/www.yelp.com\\/biz\\/schmaltz-deli-naperville\",\n" +
            "        \"business_default_color\": \"#000000\",\n" +
            "        \"website\": \"http:\\/\\/schmaltzdeli.com\\/\",\n" +
            "        \"sold_out\": \"0\",\n" +
            "        \"latitude\": 41.7976,\n" +
            "        \"longitude\": -88.118,\n" +
            "        \"expired\": \"0\",\n" +
            "        \"subscriber_only\": \"0\"\n" +
            "      }\n" +
            "     \n" +
            "]\n" +
            "}\n" +
            "\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
        JSONObject json2,json1;
        try {
            json2 = new JSONObject(str);
            json1 = json2.getJSONObject("response");
            JSONArray jsonArray = json1.getJSONArray("beams");
            Log.e("jsonArray", "" + jsonArray);
            map_details.clear();
            if(jsonArray.length()>0)
            {

                MapDetail[] contact = new MapDetail[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String subject = jsonObject.optString("subject");
                    String id = jsonObject.optString("id");
                    String latitude = jsonObject.optString("latitude");
                    String longitude = jsonObject.optString("longitude");
                    String business_facebook_url = jsonObject.optString("creator");

                    contact[i] = new MapDetail();
                    contact[i].setId(id);
                    contact[i].setSubject(subject);
                    contact[i].setLatitude(latitude);
                    contact[i].setLongitude(longitude);
                    contact[i].setBusiness_facebook_url("http://www.mygiftie.com/users/avatar.php?member_id="+business_facebook_url);

                    map_details.add(contact[i]);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onMapReady(GoogleMap googleMap1) {

        googleMap = googleMap1;
        final Marker[] m = new Marker[map_details.size()];
        eventMarkerMap = new HashMap<Marker, MapDetail>();
        final View marker_layout = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker, null);
        final ImageView im_icon = (ImageView) marker_layout.findViewById(R.id.im_icon);
        //im_icon.setImageResource(R.drawable.bg_circle);


        for (int j = 0; j < map_details.size(); j++) {
            // if(!map_details.get(j).getLatitude().equalsIgnoreCase("")&&!map_details.get(j).getLongitude().equalsIgnoreCase("")) {

            marker = new MarkerOptions().position(new LatLng(Double.parseDouble(map_details.get(j).getLatitude()), Double.parseDouble(map_details.get(j).getLongitude())));
            m[j] = googleMap.addMarker(marker);


            // Bitmap bmp1=((BitmapDrawable)im_icon.getDrawable()).getBitmap();
            //Log.v("bmp1",bmp1+"");
            //  marker.icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_btn_speak_now));


          /*  Picasso.with(this)
                    .load(map_details.get(j).getBusiness_facebook_url())
                    .placeholder(R.drawable.a)
                    .resize(80, 80)
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            Log.e("bitmap",""+bitmap);
                            ByteArrayOutputStream bytearrayoutputstream;
                            bytearrayoutputstream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytearrayoutputstream);
                            byte[] BYTE = bytearrayoutputstream.toByteArray();
                            Bitmap bitmap2 = BitmapFactory.decodeByteArray(BYTE, 0, BYTE.length);
                            try {
                                bytearrayoutputstream.close();
                            } catch (IOException e) {
                                // Intentionally blank
                            }
                            Bitmap bmp = getCircleBitmap(getResizedBitmap(bitmap2, 80, 80));

                            marker.icon(BitmapDescriptorFactory.fromBitmap(bmp));


                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {

                            Log.v("failed", "failed" + "");
                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {
                            Log.v("onprepare", "onprepare" + "");
                        }
                    });
*/


            final int finalJ = j;
            Glide.with(MainActivity.this)
                    .load(map_details.get(j).getBusiness_facebook_url())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onLoadStarted(Drawable placeholder) {
                            Log.v("glide","onLoadStarted");
                        }

                        @Override
                        public void onLoadFailed(Exception e, Drawable errorDrawable) {

                            Log.v("glide","onLoadFailed");
                        }

                        @Override
                        public void onResourceReady(final Bitmap icon1, GlideAnimation<? super Bitmap> glideAnimation) {
                            Log.v("glide","onResourceReady");
                            final LatLng TutorialsPoint = new LatLng(Double.parseDouble(map_details.get(finalJ).getLatitude()), Double.parseDouble(map_details.get(finalJ).getLongitude()));
                            Bitmap bmp = getCircleBitmap(getResizedBitmap(icon1, 80, 80));
                            googleMap.addMarker(new MarkerOptions()
                                    .position(TutorialsPoint)
                                    .icon(BitmapDescriptorFactory.fromBitmap(bmp)));
                        }

                        @Override
                        public void onLoadCleared(Drawable placeholder) {
                            Log.v("glide","onLoadCleared");
                        }
                        @Override
                        public void setRequest(Request request) {
                            Log.v("glide","setRequest");
                        }

                        @Override
                        public Request getRequest() {
                            Log.v("glide","getRequest");
                            return null;
                        }

                        @Override
                        public void onStart() {
                            Log.v("glide","onStart");
                        }

                        @Override
                        public void onStop() {
                            Log.v("glide","onStop");
                        }

                        @Override
                        public void onDestroy() {
                            Log.v("glide","onDestroy");
                        }
                    });
            //marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            eventMarkerMap.put(m[j], map_details.get(j));
            Log.v("i", j + " " + map_details.get(j).getId());
            //    Log.v("marker",eventMarkerMap.get(m[i])+" "+BoardFragment.boardlistinfo.get(i));
            LatLng coordinate = new LatLng(Double.parseDouble(map_details.get(j).getLatitude()), Double.parseDouble(map_details.get(j).getLongitude()));
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 16);
            googleMap.animateCamera(yourLocation);
            // googleMap.addMarker(marker);

            MapDetail listmodel = eventMarkerMap.get(marker);
            Log.v("datavalue", marker.getTitle() + " &&&&&&&&&&&&&&&&7");


            // }
        }
        // Add a marker in Sydney and move the camera
      /*  for(int temp = 0 ;temp<map_details.size();temp++) {
            if(!map_details.get(temp).getLatitude().equalsIgnoreCase("")&&!map_details.get(temp).getLongitude().equalsIgnoreCase("")) {
                final LatLng TutorialsPoint = new LatLng(Double.parseDouble(map_details.get(temp).getLatitude()), Double.parseDouble(map_details.get(temp).getLongitude()));

                Marker mMarker;
          *//*  BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(Integer.parseInt(map_details.get(temp).getBusiness_facebook_url()));

            MarkerOptions markerOptions = new MarkerOptions().position(TutorialsPoint)
                    .title("Current Location")
                    .snippet("Thinking of finding some thing...")
                    .icon(icon);

            mMap.addMarker(markerOptions);
*//*
          final ImageView image1 = new ImageView(this);

            Picasso.with(this).load(map_details.get(temp).getBusiness_facebook_url()).fit().centerCrop().into(image1, new com.squareup.picasso.Callback(){
                @Override
                public void onSuccess() {
                    Bitmap placePinBM = createDrawableFromView(MainActivity.this, image1);
                    mMap.addMarker(new MarkerOptions()
                            .position(TutorialsPoint)
                            .icon(BitmapDescriptorFactory.fromBitmap(placePinBM)));
                }

                @Override
                public void onError() {

                }
            });
            }*/
       /* for (int temp = 0; temp < map_details.size(); temp++) {
           if (!map_details.get(temp).getLatitude().equalsIgnoreCase("") && !map_details.get(temp).getLongitude().equalsIgnoreCase("")) {
               // final LatLng TutorialsPoint = new LatLng(Double.parseDouble(map_details.get(temp).getLatitude()), Double.parseDouble(map_details.get(temp).getLongitude()));


                mMarker.setPosition(new LatLng(Double.parseDouble(map_details.get(temp).getLatitude()),  Double.parseDouble(map_details.get(temp).getLongitude())));;
                PicassoMarker marker = new PicassoMarker(mMarker);
               URL url = null;
               try {
                   url = new URL(map_details.get(temp).getBusiness_facebook_url());
                   HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                   connection.setDoInput(true);
                   connection.connect();
                   InputStream input = connection.getInputStream();
                   Bitmap myBitmap = BitmapFactory.decodeStream(input);
                   marker.onBitmapLoaded(myBitmap,null);
                //   Picasso.with(MainActivity.this).load(map_details.get(temp).getBusiness_facebook_url()).into(marker);

               } catch (MalformedURLException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               }

           }
      }*/
        // mMap.addMarker(new MarkerOptions().position(TutorialsPoint).title("Tutorialspoint.com"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint));
    }
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);

        return resizedBitmap;
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        //  bitmap.recycle();

        return output;
    }
}