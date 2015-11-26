package com.ext.techapp.thirukkural.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ext.techapp.thirukkural.R;
import com.ext.techapp.thirukkural.xml.CoupletsXMLParser;

public class NotificationDetailActivity extends AppCompatActivity {


    TextView couplet_detail;
    private ShareActionProvider mShareActionProvider;
    CoupletsXMLParser.Couplet couplet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        couplet_detail = (TextView)findViewById(R.id.couplet_detail);

        couplet =(CoupletsXMLParser.Couplet) getIntent().getSerializableExtra("selected_couplet");


        setTitle("குறள்" + " " + couplet.getCoupletNumber());
        //set kural in text field

        StringBuilder sb = new StringBuilder();
        sb.append(couplet.getFirstLineTamil() + "<br/> " + couplet.getSecondLineTamil()).append("<br/><br/>");
        sb.append("<b>அதிகாரம் :</b> ").append(couplet.getChapterCode()+"."+couplet.getChapterNameTamil());
        sb.append("<br/><br/>");

        //set explanation text field
        sb.append("<b>மு.வ உரை</b><br/>" + couplet.getMuvaExplanation());
        sb.append("<br/><br/><br/>" +
                "<b>கலைஞர் உரை</b><br/>" + couplet.getKalaignarExplanation());
        sb.append("<br/><br/><br/>" +
                "<b>சாலமன் பாப்பையா உரை</b><br/>" + couplet.getSolomonExplanation() + "<br/><br/><br/>");


        sb.append("<b>Couplet Number </b>").append(couplet.getCoupletNumber());
        sb.append("<br/><br/>").append("<b>Translation</b>");
        sb.append("<hr>");
        sb.append("<br/><br/>").append(couplet.getFirstLineEnglish());
        sb.append("<br/>").append(couplet.getSecondLineEnglish());
        sb.append("<br/><br/>");
        sb.append("<b>Chapter Name :</b> ").append(couplet.getChapterCode() + "." + couplet.getChapterNameEnglish());
        sb.append("<br/><br/>");

        sb.append("<b>Explanation</b>").append("<br/>");
        sb.append(couplet.getEnglishExplanation());
        Spanned str = Html.fromHtml(sb.toString());
        couplet_detail.setText(str);

        couplet_detail.append("\n\n\n");

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.menu_item_detail, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        // Return true to display menu
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.menu_item_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                couplet_detail =(TextView) findViewById(R.id.couplet_detail);
                String toSend = couplet_detail.getText().toString();
                toSend = getTitle()+"\n\n"+toSend;
                sendIntent.putExtra(Intent.EXTRA_TEXT, toSend);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share via"));
                break;

            case android.R.id.home:
                navigateUpTo(getIntent());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
