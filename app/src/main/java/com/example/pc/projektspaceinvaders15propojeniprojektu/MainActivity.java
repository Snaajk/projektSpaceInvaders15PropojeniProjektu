/*
zdroj https://www.youtube.com/watch?v=mN6kM_1M0cY
postup  1                                                                //za1.
postup  2                                                                //za2.
https://www.youtube.com/watch?v=Fltdc1i6rpo
postup 3                                                                 //za3.
zdroj https://www.youtube.com/watch?v=UqtsyhASW74
nastaveni fotky na pozadi                                                //za4.

*/

package com.example.pc.projektspaceinvaders15propojeniprojektu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;




public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;                                                //za2.
    private ActionBarDrawerToggle toggle;                                             //za2.
    private Bitmap bitmap;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);                                             //za2.
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);         //za2.
        drawerLayout.addDrawerListener(toggle);                                                          //za2.
        NavigationView nvDrawer = (NavigationView)findViewById(R.id.nv);        ///////// !!!pozor tady jem mozna problem!!!
        toggle.syncState();                                                                              //za2.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);                                          //za2.
        setupDrawerContent(nvDrawer);
    }

    public Context getContext(){
        return context;
    }

    public MainActivity getActivity(){
        return this;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {                                               //za2.
        if(toggle.onOptionsItemSelected(item)){                                                          //za2.
            System.out.println("jedu");                                                                   //za2.
            return true;                                                                              //za2.
        }
        return super.onOptionsItemSelected(item);                                                   //za2.
    }

    public void selectItemDrawer(MenuItem menuItem){                                    //za3 4.
        Fragment myfragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.plocha:
                fragmentClass = Plocha.class;
                break;
            case R.id.hra:
                fragmentClass = Hra.class;
                break;
            case R.id.foto:
                fragmentClass = Foto.class;
//                fragmentClass.
//                        Foto foto = (Foto)fragmentClass.getClass();

//                Foto foto = new Foto();
//                fragmentClass.asSubclass(foto);


//                Foto foto = new Foto();
//                bitmap = foto.getBitmap();
//                foto();



                break;
            case R.id.nastaveni:
                fragmentClass = Nastaveni.class;


                break;
            case R.id.konec:
                fragmentClass = Konec.class;
                // dal jsem exit
                System.exit(0);

                break;
            default:
                fragmentClass = Plocha.class;
        }
        try {                                                                                   //5.
            myfragment = (Fragment)fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();                              //6.
        fragmentManager.beginTransaction().replace(R.id.flcontent, myfragment).commit();
//        fragmentManager.findFragmentById()

        /*FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(Foto)
*/

        foto(myfragment, fragmentManager);


        menuItem.setCheckable(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectItemDrawer(menuItem);
                return true;
            }
        });
    }

    public void foto2(Fragment myfragment, FragmentManager fragmentManager){

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa"+ myfragment.toString() );
        if (myfragment instanceof Foto) {
            Foto foto = (Foto) myfragment;
//            foto.foceni();
//            myfragment.findFragmentById()



            //------------------------------- novy pokus zacatek

            bitmap = foto.getBitmap();

//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+ headlinesFragment.getVyfoceno());
//            ImageView imageView = (ImageView) findViewById(R.id.imageView2);
//            imageView.setImageBitmap(bitmap);

            if(bitmap != null){

                FrameLayout imageView2 = (FrameLayout) findViewById(R.id.flcontent);
                BitmapDrawable ob = new BitmapDrawable(getResources(), bitmap);
                imageView2.setBackground(ob);
                System.out.println("??????????????????????????????????????????????????????????????????");

            }else {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! vyfoceno "+foto.getVyfoceno());
            }
        }
    }

    public void foto(Fragment myfragment, FragmentManager fragmentManager){
        /*if (myfragment instanceof Foto) {
            Foto foto  = (Foto)fragmentManager.getFragments();


            if(foto.getVyfoceno() == true){
                bitmap = foto.getBitmap();

                FrameLayout imageView2 = (FrameLayout) findViewById(R.id.flcontent);
                BitmapDrawable ob = new BitmapDrawable(getResources(), bitmap);
                imageView2.setBackground(ob);

            }

        }*/
    }

    @Override
    protected void onResume() {                                                                     //za6.
//        initListeners();
        super.onResume();
//        staraSpaceInvadersView.resume();                                                                 //za7.
    }


    public void setFoto(Bitmap bitmap){                                        //za4.
        this.bitmap = bitmap;
        FrameLayout imageView2 = (FrameLayout) findViewById(R.id.flcontent);
        BitmapDrawable ob = new BitmapDrawable(getResources(), bitmap);
        imageView2.setBackground(ob);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        /*if (fragment instanceof Foto) {
            Foto headlinesFragment = (Foto) fragment;
            headlinesFragment.getVyfoceno();
        }*/
    }

}
/*
//za1.
1.
klepnete na slozku v Gradle Scripts v ni je build.gradle{module: app)
tam jsem pridal
implementation 'com.android.support:design:28.0.0'

2.
potom ve slozce "pokus10menuProHru\app\src\main\res" je colors.xml
tam jsou barvy a ja jeste pridal krokm tech co tam uz jsou jeste
    <color name="darkgray">#454545</color>
    <color name="white">#ffffff</color>

3.
 layout v activity_main.xml
 tam zmen layout na
 <android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"

 3.1
 dale jsem do nej vlozil
 <android.support.design.widget.NavigationView
 mrkni se na presne nastaveni tam, nebudu to tu prepisovat cele

4.
do slozky "res" jem pridal novou slozku "menu"
 4.1 do ni soubor typu "menu resouce file" jmeno "drawermenu"
 4.2 do napis
 <item android:id="@+id/db" android:title="dashboared" />
 to jsou nejspis radky v nabidce menu
 4.3
 potom jsem stahl z webu https://www.flaticon.com/packs/dashboard nejake ikony
 4.4
 ikony se vkladaji klikni na slozku "drawable"
 dej new>vector asset
 nastav lokalni adresar a vlos ikony
 4.5
 cas videa 8:10
 potom v "drawermenu.xml" icony pripoj
 android:icon="@drawable/ic_database"
 takze vysledek bude vypadat takhle
 <item android:id="@+id/db" android:title="dashboared" android:icon="@drawable/ic_database"/>
 4.6
 do activity-main.xml pridej do casti <android.support.design.widget.NavigationView
 app:menu="@menu/drawermenu"
 /////////////////////////////////////////////////////// FUNGUJE JE TO ZATIM STATICKE MENU/////////////////////////////

 5.
 do activity-main.xml pridej do casti <android.support.design.widget.NavigationView
 android:layout_gravity="start"

 6.
 v activity-main.xml se prepni do grafickeho rozhrani talcitkko disign
 nahore jsou vybery telefonu a zobrazeni "device for preview" rozklikni
 tam jsem vybral neco s AVD, v navodu byl jiny telefon ale ja ho tam namam v nabidce tak jsem dal tohle
 AVD je asi ten mobil co je v AVD manageru
 /////////////////////////////////////////////////////// FUNGUJE ZATAHOVACI MENU VYTAHNI HO Z PRAVA/////////////////////////////

7.??????????
cas 10:22
v tutriallu se maze najake nastaveni ale ja ho tam namam tak to nechavam tak

8.
vytvoreni noveho layoutu
je dej nad layoutem new a layout neco neco
 8.1
 nastav
 android:layout_height="160dp">
 8.2
 nakopiruj nejaky obrayek do drawable pozor ad je ve spravne slozce v drawable jich je vic, nemusi to fungovat
 je to s tim nekdy sloaite a uz se stalo ze to nefunguje
 8.3.
 pridej radek
 android:background="@drawable/obrazek1"
 8.4
 dalsi obrazek nakopiruj do drwable
8.5........................................... je asi zbytecne psat uplne vse kdyz tak 12:38 minute

9.
v activity-main.xml
pridal jsem casti <android.support.design.widget.NavigationView
radek app:headerLayout="@layout/header"
tohle prida ten novy layout do horni casti casti toho prvniho

10.
v activity-main.xml
pridal jsem tag <LinearLayout
toto je pridani pozadi

//za2.
programovani v MainActivity

1.
pridal jsem dve private tridy

2.
v activity_main.xml
jsem pridal radek android:id="@+id/drawer"
tady jsem doplnil
drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

3.
ve slozce values>strings.xml
jsem pridal
<string name="open">Open</string>
<string name="close">Close</string>

4.
pridal jsem tohle
drawerLayout.addDrawerListener(toggle);
toggle.syncState();
nevim presne jak funguji ty dve horni
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
kdys se prida tohle tak se yobrazi kosticka v levem hornim rohu ktera se zmeni na sipku

5.
pridal jsem metodu

//za3
1.
dej pravem na app potom new > fragment > fragment(blank) a jmeno jsem dal Dashboared
pro kazde tlacitko v nabidce

2.
vymaz kod v metode "onAttach" u tech trid co jsi vytvoril, krome toho super

3.
nastavil jsem "fragmet_settings.xml" tag "<TextView" je tam velikost tekstu a tak
 3.1.
 to same u dalsich fragmentu

4.
cas 7:33
napsal jsem novou metodu public void selectItemDrawer(MenuItem menuItem){
do ni jsem dal switch ve ktere jsou odkazy na jednotlive tlacitka
odkazy jdou presne na soubor drawermenu.xml
tam jsou ty tagy tak to na ne naves

5.
vlozil jsem
myfragment = (Fragment)fragmentClass.newInstance();
a potom je nutno to dat do tray catch

6.
prepsani existujici tridy novou instanci

7.
layout v activity_main.xml
je tam cast
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
v ni je
<LinearLayout
ten prepis na
 <FrameLayoutLayout
 do napis
 android:id="@+id/flcontent"

 8.
 vytvoril jsem metodu
  private void setupDrawerContent(NavigationView navigationView){
 a pak jsem metodu umistil do onCreate()

//za4.
ve Fragmentu Foto jsem zadal kod
((MainActivity)getActivity()).setFoto(bitmap);
a v tady v MainActivity funkci ktera obrazek nastavi na pozadi


*/