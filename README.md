# แอพพลิเคชั่น แจ้งเบาะแสการกระทำผิดและอุบัติเหตุ (Hawkeye)

### ความสามารถของแอพพลิเคชั่น
- ล็อกอินผ่านเฟสบุค
- โพสข้อมูลการกระทำผิดกฎหมายและอุบัติเหตุต่างๆ พร้อมกับเลือกชนิดของโพสนั้น 
- ระบุพิกัดที่เกิดเหตุการณ์ขึ้น
- ดูเหตุการณ์โดยแบ่งตามประเภทต่างๆ
- คอมเม้นในโพสของผู้ใช้อื่นๆเพื่อแจ้งเบาะแสเพิ่มเติม

### User Interface ทั้งหมดของแอพพลิเคชั่น

<img src="https://github.com/bennyy2/final_project_android/blob/master/UI%20for%20wiki/Screenshot_2017-11-30-14-17-30.png" width="100"/>
<img src="https://github.com/bennyy2/final_project_android/blob/master/UI%20for%20wiki/Screenshot_2017-11-30-14-17-54.png" width="100"/>
<img src="https://github.com/bennyy2/final_project_android/blob/master/UI%20for%20wiki/Screenshot_2017-11-30-14-18-25.png" width="100"/>
<img src="https://github.com/bennyy2/final_project_android/blob/master/UI%20for%20wiki/Screenshot_2017-11-30-14-18-33.png" width="100"/>
<img src="https://github.com/bennyy2/final_project_android/blob/master/UI%20for%20wiki/Screenshot_2017-11-30-14-18-46.png" width="100"/>
<img src="https://github.com/bennyy2/final_project_android/blob/master/UI%20for%20wiki/Screenshot_2017-11-30-14-19-44.png" width="100"/>
<img src="https://github.com/bennyy2/final_project_android/blob/master/UI%20for%20wiki/Screenshot_2017-11-30-14-20-02.png" width="100"/>


### การทำงานของ UI ในแต่ละหน้า

<img src="https://github.com/bennyy2/final_project_android/blob/master/UI%20for%20wiki/Screenshot_2017-11-30-14-17-30.png" width="200"/>


หน้าแรกของแอพพลิเคชั่น กด continue with facebook เพื่อล็อกอินเข้าใช้งาน


<img src="https://github.com/bennyy2/final_project_android/blob/master/UI%20for%20wiki/Screenshot_2017-11-30-14-17-54.png" width="200"/>

หน้า New Feed แสดงการโพสทั้งหมด


<img src="https://github.com/bennyy2/final_project_android/blob/master/UI%20for%20wiki/Screenshot_2017-11-30-14-18-25.png" width="200"/>

อ่านโพสเพิ่มเติมโดยการคลิกที่ ListView นั้น ในหน้านี้จะแสดงข้อมูลทั้งหมดของโพส พร้อมกับพิกัดที่เกิดเหตุเหตุ และสามารถเขียนความคิดเห็นเพิ่มเติมได้จากหน้านี้


<img src="https://github.com/bennyy2/final_project_android/blob/master/UI%20for%20wiki/Screenshot_2017-11-30-14-18-33.png" width="200"/>

หน้า New post เมื่อกด foatingButton ในหน้า New feed จะเข้าสู่หน้า Add new post โดยผู้ใช้จะสามารถเพิ่มโพสใหม่ เลือกชนิดของเหตุการณ์ที่เกิดขึ้น และระบุพิกัดของเหตุการณ์


<img src="https://github.com/bennyy2/final_project_android/blob/master/UI%20for%20wiki/Screenshot_2017-11-30-14-18-46.png" width="200"/>

เลือกสถานที่ที่เกิดเหตุการณ์เพื่ออ้างอิงในการเพิ่มโพส


<img src="https://github.com/bennyy2/final_project_android/blob/master/UI%20for%20wiki/Screenshot_2017-11-30-14-19-44.png" width="200"/>

หน้า Nearby event เป็นหน้าที่โชว์เหตุการณ์ทั้งหมดแบ่งตามชนิดที่ผู้ใช้เลือกแล้วจึงกดปุ่ม search เพื่อทำการค้นหาสถานที่เกิดขึ้นทั้งหมดของชนิดนั้น


<img src="https://github.com/bennyy2/final_project_android/blob/master/UI%20for%20wiki/Screenshot_2017-11-30-14-20-02.png" width="200"/>

Nav bar ที่แสดงข้อมูลผู้ใช้ และเมนูทั้งหมด โดยผู้ใช้สามารถล็อกเอ้าได้จากหน้านี้

## ชุดการทดสอบ
AddPostTest
- Test 1 postIsEmpty : ตรวจสอบค่าว่างในการเพิ่มโพสใหม่
- Test 2 postIsSuccess : ทดสอบใส่ค่อที่ถูกต้อง

## APIs ที่ใช้งาน

- Facebook : https://developers.facebook.com/docs/facebook-login/android

```java
mCallbackManager = CallbackManager.Factory.create();
loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>(){
            @Override
            public void onSuccess(LoginResult loginResult) {
                showLoadingView();
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
```

- Firebase Authenticate Using Facebook login : https://firebase.google.com/docs/auth/android/facebook-login?authuser=0 

```java
private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

        mAuth.signInWithCredential(credential)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            signInFaceBook(user);

                        } else {

                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            signInFaceBook(null);
                        }

                    }
                });
    }
```

- Firebase Real Time Database : https://firebase.google.com/docs/database/android/start/?authuser=0

```java
public void saveUserData(){

        databaseReference = FirebaseDatabase.getInstance().getReference("user/");

        databaseReference.child(this.id).child("display_name").setValue(this.display_name);
        databaseReference.child(this.id).child("id").setValue(this.id);
        databaseReference.child(this.id).child("email").setValue(this.email);
        databaseReference.child(this.id).child("image_url").setValue(this.image_url);
    }

    public void checkUser(){
        databaseReference = FirebaseDatabase.getInstance().getReference("user/");
        databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean status = true;

                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    if (data.getKey().equals(id)) {
                        status = true;
                        break;
                    } else {
                        status = false;
                    }
                }

                if (listener != null) {
                    listener.onCheckedUser(status);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("Status", "onCancelled");
            }


        });
    }
```

- Google map with marker : https://developers.google.com/maps/documentation/android-api/map-with-marker

```java
private void initMap(){

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }
@Override

    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;
        pinLocation(post.getLatitude(), post.getLongtitude(), (float) 15.0);
    }

    private void pinLocation(Double latitude, Double longtitude, Float zoom) {
        LatLng latLng = new LatLng(latitude, longtitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mGoogleMap.moveCamera(cameraUpdate);
        MarkerOptions options = new MarkerOptions().title("Post's Location")
                .position(new LatLng(latitude, longtitude));
        mGoogleMap.addMarker(options);
    }
```

### Link ไฟล์ APK
https://drive.google.com/open?id=1K7RNRN2Y-yHQO8GHKhjwfahm7pQWqiFj

### Link โหลดจาก play store
https://play.google.com/store/apps/details?id=kmitl.project.benjarat58070079.hawkeyes

## VDO
### Link VDO การนำเสนอผลงาน + การทดสอบ app ด้วย Monkey
https://youtu.be/eW7kLJHAG2M


### Link VDO รีวิวแอพพลิเคชั่นจากผู้ใช้งาน
https://youtu.be/aFqGkp_8qy8


