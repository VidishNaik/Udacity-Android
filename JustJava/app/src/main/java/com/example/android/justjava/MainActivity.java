package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 2;
    boolean checked_whippedCream=false;
    boolean checked_chocolate=false;
    int wc=0;
    int ch=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void check(View view)
    {
        CheckBox c=(CheckBox) findViewById(R.id.checkbox_whipped_cream);
        if(c.isChecked())
        {
            checked_whippedCream=true;
            wc=1;
        }
        else
        {
            checked_whippedCream=false;
            wc=0;
        }
        c=(CheckBox) findViewById(R.id.checkbox_chocolate);
        if(c.isChecked())
        {
            checked_chocolate=true;
            ch=2;
        }
        else
        {
            checked_chocolate=false;
            ch=0;
        }
        displayQuantity(quantity);
        displayPrice((quantity*(5+wc+ch)));
    }
    public void increment(View view)
    {
        if (quantity<20)
        {
            check(view);
            quantity = quantity + 1;
            displayQuantity(quantity);
            displayPrice((quantity * (5 + wc + ch)));
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Maximum Order Limit Reached",Toast.LENGTH_SHORT).show();
        }
    }
    public void decrement(View view)
    {
        if(quantity>1)
        {
            check(view);
            quantity = quantity - 1;
            displayQuantity(quantity);
            displayPrice((quantity * (5 + wc + ch)));
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Minimum Order Limit Reached",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        check(view);
        EditText e= (EditText) findViewById(R.id.name);

       String priceMessage="Name:"+e.getText()+"\nQuantity: "+quantity+"\nAdd Whipped Cream? "+checked_whippedCream+
               "\nAdd Chocolate? "+checked_chocolate+"\nTotal: $"+(quantity*(5+wc+ch))+"\nThank You!!!";
        String string[]={"fakeidforupload@gmail.com"};
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,string);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java Order for "+e.getText());
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if(intent.resolveActivity(getPackageManager())!= null)
        {
            startActivity(intent);
        }
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}