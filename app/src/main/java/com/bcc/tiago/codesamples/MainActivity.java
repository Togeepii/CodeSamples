package com.bcc.tiago.codesamples;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

import info.hoang8f.widget.FButton;

public class MainActivity extends Activity {
    private String[] mCodeExamplesTitles;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setIcon(R.drawable.ic_action_bar);
        //set fragment
        mainFragment fragment = new mainFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        mTitle = getTitle();
        mDrawerTitle = getString(R.string.nav_title);
        mCodeExamplesTitles = getResources().getStringArray(R.array.items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_navigation_drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_listview_item, mCodeExamplesTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // se verdade, actionbartoggle fez o handle do app icon
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setActionBarTitle(String title) {
        getActionBar().setTitle(title);
    }

    //fragments
    public static class mainFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_main, container, false);
            ((MainActivity) getActivity()).setActionBarTitle("Início");
            return view;
        }
    }

    public static class functionsFragment extends ListFragment {
        String content;

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            String[] funcList = getResources().getStringArray(R.array.funcList);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, funcList);
            setListAdapter(adapter);
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);
            Bundle bundle = new Bundle();
            contentFragment fragment = new contentFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            switch (position) {
                //if case
                case 0:
                    bundle.putString("index", "l1");
                    content = getResources().getString(R.string.content_se);
                    break;
                //for case
                case 1:
                    bundle.putString("index", "l2");
                    content = getResources().getString(R.string.content_for);
                    break;
                //E e OU sort case
                case 2:
                    bundle.putString("index", "l3");
                    content = getResources().getString(R.string.content_and_or);
                    break;
                //bubble case
                case 3:
                    bundle.putString("index", "l4");
                    content = getResources().getString(R.string.content_bubble);
                    break;
            }
            bundle.putString("content", content);
            fragment.setArguments(bundle);
            ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack(null).commit();
        }
    }

    public static class geoFragment extends ListFragment {
        String content;

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            String[] funcList = getResources().getStringArray(R.array.geoList);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, funcList);
            setListAdapter(adapter);
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);
            Bundle bundle = new Bundle();
            contentFragment fragment = new contentFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            switch (position) {
                //if case
                case 0:
                    bundle.putString("index", "g1");
                    content = getResources().getString(R.string.content_triang);
                    break;
                //for case
                case 1:
                    bundle.putString("index", "g2");

                    break;
                //E e OU sort case
                case 2:
                    bundle.putString("index", "g3");

                    break;
                //bubble case
                case 3:
                    bundle.putString("index", "g4");

                    break;
            }
            bundle.putString("content", content);
            fragment.setArguments(bundle);
            ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
            ft.replace(R.id.content_frame, fragment)
                    .addToBackStack(null)
                    .commit();
            ;
        }
    }

    //CONTENT
    public static class contentFragment extends Fragment {
        String content, index;
        FloatingActionButton bExample;
        View view;
        boolean isbVisible;
        @Override
        public void onPause() {
            super.onPause();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_content, container, false);
            //set views
            TextViewEx tvContent = (TextViewEx) view.findViewById(R.id.tvContent);
            bExample = (FloatingActionButton) view.findViewById(R.id.bExample);
            isbVisible=true;
            tvContent.setText(content, true);
            tvContent.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(isbVisible){
                        isbVisible=false;
                        bExample.hide();
                    }else{
                        isbVisible=true;
                        bExample.show();
                    }
                    return true;
                }
            });
            bExample.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
                    switch (index.charAt(0)) {
                        case 'l':
                            switch (index.charAt(1)) {
                                case '1':
                                    ifFragment ifFragment = new ifFragment();
                                    ft.replace(R.id.content_frame, ifFragment).addToBackStack(null).commit();
                                    break;
                                case '2':
                                    forFragment forFragment = new forFragment();
                                    ft.replace(R.id.content_frame, forFragment).addToBackStack(null).commit();
                                    break;
                                case '3':
                                    andorFragment andorFragment = new andorFragment();
                                    ft.replace(R.id.content_frame, andorFragment).addToBackStack(null).commit();
                                    break;
                                case '4':
                                    bubbleFragment bubbleFragment = new bubbleFragment();
                                    ft.replace(R.id.content_frame, bubbleFragment).addToBackStack(null).commit();
                                    break;
                            }
                            break;
                        case 'g':
                            switch (index.charAt(1)) {
                                case '1':
                                    triangFragment triangFragment = new triangFragment();
                                    ft.replace(R.id.content_frame, triangFragment).addToBackStack(null).commit();
                                    break;
                                case '2':
                                    break;
                            }
                            break;
                    }
                }
            });
            return view;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //get content from previous fragment
            Bundle bundle = this.getArguments();
            content = bundle.getString("content", "erro no engano");
            index = bundle.getString("index", "error");
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
        }
    }


    //region Inner Fragments
    public static class ifFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.fragment_if, container, false);
            final TextView tvPressed = (TextView) view.findViewById(R.id.tvIfPressed);
            final TextView tvNormal = (TextView) view.findViewById(R.id.tvIfNormal);
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        tvPressed.setVisibility(View.VISIBLE);
                        tvNormal.setVisibility(View.INVISIBLE);
                        Random rand = new Random();
                        int red = rand.nextInt(256);
                        int green = rand.nextInt(256);
                        int blue = rand.nextInt(256);

                        //mix color
                        red = (red + 255) / 2;
                        green = (green + 255) / 2;
                        blue = (blue + 255) / 2;
                        v.setBackgroundColor(Color.rgb(red, green, blue));
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        Log.d("UP", "UP");
                        tvPressed.setVisibility(View.INVISIBLE);
                        tvNormal.setVisibility(View.VISIBLE);
                        v.setBackgroundColor(Color.WHITE);
                    }
                    return true;
                }
            });
            return view;

        }

    }

    public static class forFragment extends Fragment {
        EditText etForNumber;
        FButton bForCalc;
        TextView tvResp;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.fragment_for, container, false);

            //init
            etForNumber = (EditText) view.findViewById(R.id.etForNumber);
            tvResp = (TextView) view.findViewById(R.id.tvResp);
            bForCalc = (FButton) view.findViewById(R.id.bForCalc);
            bForCalc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (etForNumber.getText().toString().trim().length() > 0) {
                        //colorir textview
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tvResp.setTextColor(Color.WHITE);
                            }
                        }, 1000);
                        final Random rand = new Random();
                        int vezes = Integer.parseInt(etForNumber.getText().toString());

                        //mudar background pra cada número
                        for (int i = 0; i <= vezes; i++) {

                            //esperar 1 segundo a cada troca
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    int red = rand.nextInt(256);
                                    int green = rand.nextInt(256);
                                    int blue = rand.nextInt(256);

                                    //mix color
                                    red = (red + 255) / 2;
                                    green = (green + 255) / 2;
                                    blue = (blue + 255) / 2;
                                    view.setBackgroundColor(Color.rgb(red, green, blue));
                                    Log.e("waaat", "color");

                                }
                            }, 1000 * (i + 1));
                        }
                    }
                }
            });
            return view;
        }
    }

    public static class andorFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
        CheckBox cbUm, cbDois, cbTres, cbQuatro;
        TextView tvAndor;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_andor, container, false);
            cbUm = (CheckBox) view.findViewById(R.id.cbUm);
            cbUm.setOnCheckedChangeListener(this);

            cbDois = (CheckBox) view.findViewById(R.id.cbDois);
            cbDois.setOnCheckedChangeListener(this);

            cbTres = (CheckBox) view.findViewById(R.id.cbTres);
            cbTres.setOnCheckedChangeListener(this);

            cbQuatro = (CheckBox) view.findViewById(R.id.cbQuatro);
            cbQuatro.setOnCheckedChangeListener(this);

            tvAndor = (TextView) view.findViewById(R.id.tvAndor);

            return view;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.cbUm:
                    Log.e("yay", "yay");
                    if (checkConditions()) {
                        tvAndor.setVisibility(View.VISIBLE);
                    } else {
                        tvAndor.setVisibility(View.INVISIBLE);
                    }
                    break;
                case R.id.cbDois:
                    if (checkConditions()) {
                        tvAndor.setVisibility(View.VISIBLE);
                    } else {
                        tvAndor.setVisibility(View.INVISIBLE);
                    }
                    break;
                case R.id.cbTres:
                    if (checkConditions()) {
                        tvAndor.setVisibility(View.VISIBLE);
                    } else {
                        tvAndor.setVisibility(View.INVISIBLE);
                    }
                    break;
                case R.id.cbQuatro:
                    if (checkConditions()) {
                        tvAndor.setVisibility(View.VISIBLE);
                    } else {
                        tvAndor.setVisibility(View.INVISIBLE);
                    }
                    break;
            }
        }

        private boolean checkConditions() {
            if ((cbUm.isChecked() || cbDois.isChecked()) && (cbTres.isChecked() || cbQuatro.isChecked())) {
                Log.e("yay", "yay");
                return true;
            } else {
                return false;
            }
        }
    }

    public static class bubbleFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_bubble, container, false);
            final EditText etNumbers = (EditText) view.findViewById(R.id.etBubbleNumbers);
            Button bCalc = (Button) view.findViewById(R.id.bBubbleCalc);
            final TextView tvResult = (TextView) view.findViewById(R.id.tvBubbleResult);
            bCalc.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             //TODO impedir usuário de colocar letras
                                             if (etNumbers.getText().toString().trim().length() > 0) {
                                                 try {

                                                     String[] nArray = null;

                                                     String numbers = etNumbers.getText().toString();
                                                     numbers = numbers.replace(" ", "");
                                                     int n = numbers.length();
                                                     if (numbers.contains(",")) {
                                                         nArray = numbers.split(",");
                                                     }
                                                     double[] finalArray = new double[nArray.length];
                                                     for (int i = 0; i < nArray.length; i++) {
                                                         finalArray[i] = Double.parseDouble(nArray[i]);
                                                     }
                                                     int length = finalArray.length;
                                                     double[] sortArray = bubbleSort(finalArray, length);
                                                     String resultado = String.valueOf(sortArray[0]);
                                                     for (int i = 1; i < sortArray.length; i++) {
                                                         resultado = resultado + " < " + String.valueOf(sortArray[i]);
                                                     }
                                                     tvResult.setText(resultado);
                                                     //Toast.makeText(getActivity(), resultado, Toast.LENGTH_LONG).show();
                                                     Log.e("teste", resultado);
                                                 } catch (Exception e) {

                                                 }
                                             } else {

                                             }
                                         }
                                     }

            );
            return view;
        }

        //bubble sort function
        public double[] bubbleSort(double[] vetor, int tamanho) {
            int comp = 0;
            int trocas = 0;
            double aux;
            for (int i = 0; i < tamanho; i++) {
                for (int j = 0; j < tamanho - 1; j++) {
                    comp++;
                    if (vetor[j] > vetor[j + 1]) {
                        aux = vetor[j];
                        vetor[j] = vetor[j + 1];
                        vetor[j + 1] = aux;
                        trocas++;
                    }
                }
            }
            return vetor;
        }
    }

    public static class triangFragment extends Fragment {
        EditText etXum, etXdois, etXtres, etYum, etYdois, etYtres;
        Button calcTriang;
        //variaveis de calculo
        double x1, x2, x3, y1, y2, y3, a, b, c, d, f, g, h, j, k, l, a1, a2, a3, cos1, cos2, cos3, d1, d2, d3, d4, d5, d6, at;

        public static double round(double value, int places) {
            if (places < 0) throw new IllegalArgumentException();

            BigDecimal bd = new BigDecimal(value);
            bd = bd.setScale(places, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_triangulo, container, false);
            setVars(view);
            calcTriang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String[] etText = {etXum.getText().toString(),
                            etXdois.getText().toString(),
                            etXtres.getText().toString(),
                            etYum.getText().toString(),
                            etYdois.getText().toString(),
                            etYtres.getText().toString()};

                    for (int i = 0; i < 6; i++) {
                        if (isEmpty(etText[i])) {
                            Toast.makeText(getActivity(), "Preencha todos os dados!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    try {
                        //coordenadas
                        x1 = Double.parseDouble(etText[0]);
                        x2 = Double.parseDouble(etText[1]);
                        x3 = Double.parseDouble(etText[2]);
                        y1 = Double.parseDouble(etText[3]);
                        y2 = Double.parseDouble(etText[4]);
                        y3 = Double.parseDouble(etText[5]);

                        //
                        a = x1 - x2;
                        b = x1 - x3;
                        c = x2 - x3;
                        d = y1 - y2;
                        f = y1 - y3;
                        g = y2 - y3;

                        d1 = x1 * y2;
                        d2 = y1 * x3;
                        d3 = x2 * y3;
                        d4 = y1 * x2;
                        d5 = x1 * y3;
                        d6 = y2 * x3;

                        at = (d1 + d2 + d3 - d4 - d5 - d6) / 2;

                        if (at < 0) at = at * -1;

                        if (a < 0) a = a * -1;

                        if (b < 0) b = b * -1;

                        if (c < 0) c = c * -1;

                        if (d < 0) d = d * -1;

                        if (f < 0) f = f * -1;

                        if (g < 0) g = g * -1;

                        //bundle para passar pro próximo fragment
                        Bundle bundle = new Bundle();

                        //round number
                        DecimalFormat df = new DecimalFormat("#.##");
                        j = Math.pow(a, 2) + Math.pow(d, 2);
                        j = Math.sqrt(j);
                        j = round(j, 2);
                        bundle.putDouble("a", j);
                        //colocar lado a

                        k = Math.pow(b, 2) + Math.pow(f, 2);
                        k = Math.sqrt(k);
                        k = round(k, 2);
                        bundle.putDouble("b", k);
                        //lado b

                        l = Math.pow(c, 2) + Math.pow(g, 2);
                        l = Math.sqrt(l);
                        l = round(l, 2);
                        bundle.putDouble("c", l);
                        //lado c

                        h = j + k + l;
                        h = round(h, 2);
                        bundle.putDouble("perimetro", h);
                        //perimetro

                        cos1 = (Math.pow(j, 2) - Math.pow(k, 2) - Math.pow(l, 2)) / (-2 * k * l);
                        cos2 = (Math.pow(k, 2) - Math.pow(j, 2) - Math.pow(l, 2)) / (-2 * j * l);
                        cos3 = (Math.pow(l, 2) - Math.pow(k, 2) - Math.pow(j, 2)) / (-2 * k * j);

                        a1 = Math.acos(cos1) * 57.29577951308233;
                        a2 = Math.acos(cos2) * 57.29577951308233;
                        a3 = 180 - a1 - a2;

                        if (cos1 == 0) {
                            bundle.putDouble("ang1", 90);
                            //90 1
                        } else {
                            a1 = round(a1, 0);
                            bundle.putDouble("ang1", a1);
                            //ang1
                        }
                        if (cos2 == 0) {
                            bundle.putDouble("ang2", 90);
                            //90 1
                        } else {
                            a2 = round(a2, 0);
                            bundle.putDouble("ang2", a2);
                            //ang2
                        }
                        if (cos3 == 0) {
                            bundle.putDouble("ang3", 90);
                            //90 ang 3
                        } else {
                            a3 = round(a3, 0);
                            bundle.putDouble("ang3", a3);
                            //ang 3
                        }

                        //area
                        at = round(at, 2);
                        bundle.putDouble("area", at);

                        //result fragment
                        triangResultFragment triangResultFragment = new triangResultFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        triangResultFragment.setArguments(bundle);
                        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out,
                                android.R.animator.fade_in, android.R.animator.fade_out)
                                .replace(R.id.content_frame, triangResultFragment).addToBackStack(null).commit();
                    } catch (Exception e) {

                    }
                }
            });
            return view;
        }

        public boolean isEmpty(String text) {
            if (text.trim().length() > 0) {
                return false;
            } else {
                return true;
            }
        }

        public void setVars(View view) {
            etXum = (EditText) view.findViewById(R.id.etXum);
            etXdois = (EditText) view.findViewById(R.id.etXdois);
            etXtres = (EditText) view.findViewById(R.id.etXtres);
            etYum = (EditText) view.findViewById(R.id.etYum);
            etYdois = (EditText) view.findViewById(R.id.etYdois);
            etYtres = (EditText) view.findViewById(R.id.etYtres);
            calcTriang = (Button) view.findViewById(R.id.bCalcTriangulo);
        }
    }

    public static class triangResultFragment extends Fragment {
        Bundle bundle;
        TextView tvAngUm, tvAngDois, tvAngTres, tvLadoA, tvLadoB, tvLadoC, tvPerimetro, tvArea;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_triangulo_resultado, container, false);
            init(view);
            return view;
        }

        private void init(View view) {
            tvAngUm = (TextView) view.findViewById(R.id.tvAngUm);
            tvAngUm.setText(String.valueOf(bundle.getDouble("ang1")));

            tvAngDois = (TextView) view.findViewById(R.id.tvAngDois);
            tvAngDois.setText(String.valueOf(bundle.getDouble("ang2")));

            tvAngTres = (TextView) view.findViewById(R.id.tvAngTres);
            tvAngTres.setText(String.valueOf(bundle.getDouble("ang3")));

            tvLadoA = (TextView) view.findViewById(R.id.tvLadoA);
            tvLadoA.setText(String.valueOf(bundle.getDouble("a")));

            tvLadoB = (TextView) view.findViewById(R.id.tvLadoB);
            tvLadoB.setText(String.valueOf(bundle.getDouble("b")));

            tvLadoC = (TextView) view.findViewById(R.id.tvLadoC);
            tvLadoC.setText(String.valueOf(bundle.getDouble("c")));

            tvPerimetro = (TextView) view.findViewById(R.id.tvPerimetro);
            tvPerimetro.setText(String.valueOf(bundle.getDouble("perimetro")));

            tvArea = (TextView) view.findViewById(R.id.tvArea);
            tvArea.setText(String.valueOf(bundle.getDouble("area")));

        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            bundle = this.getArguments();
        }
    }
//endregion

    //List click
    class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);
            switch (position) {
                case 0:
                    mainFragment mainFragment = new mainFragment();
                    ft.replace(R.id.content_frame, mainFragment).addToBackStack(null).commit();
                    break;
                case 1:
                    functionsFragment funcFrag = new functionsFragment();
                    ft.replace(R.id.content_frame, funcFrag).addToBackStack(null).commit();
                    break;
                case 2:
                    geoFragment geoFrag = new geoFragment();
                    ft.replace(R.id.content_frame, geoFrag).addToBackStack(null).commit();
                    break;
            }
            mDrawerList.setItemChecked(position, true);
            mTitle = mCodeExamplesTitles[position];
            getActionBar().setTitle(mTitle);
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }
}

