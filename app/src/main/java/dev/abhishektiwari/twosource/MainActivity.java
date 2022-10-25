package dev.abhishektiwari.twosource;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<TradeSummeryModel> summeryModels;
    RecyclerView list ;
    private JSONArray data;
    private JSONArray data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.List);




        data1 = null;
        try {
            data1 = new JSONArray("[{\"userid\":\"CMGHG01\",\"clientshare\":50,\"brokershare\":60,\"companyshare\":70,\"netmtm\":80}," +
                    "{\"userid\":\"CMGHG01\",\"clientshare\":50,\"brokershare\":60,\"companyshare\":70,\"netmtm\":80}," +
                    "{\"userid\":\"USER2\",\"clientshare\":50,\"brokershare\":60,\"companyshare\":70,\"netmtm\":80}," +
                    "{\"userid\":\"USER1\",\"clientshare\":50,\"brokershare\":60,\"companyshare\":70,\"netmtm\":80}," +
                    "{\"userid\":\"CMGHG01\",\"clientshare\":50,\"brokershare\":60,\"companyshare\":70,\"netmtm\":80}]");

            System.out.println("data1 " +data1 );
        } catch (JSONException e) {
            e.printStackTrace( );
        }


        //////////////////

















        /////////////////



        //data jsonArray
        data = null;
        try {
            data = new JSONArray("[{\"srno\":1,\"userid\":\"CMGHG01\",\"accountcode\":\"CODE\",\"symbol\":\"NIFTY\",\"bfq\":\"1\",\"netq\":\"1\",\"cfq\":\"3\",\"expirydate\":\"11JAN2022\",\"strikeprice\":\"PE\"},{\"srno\":1,\"userid\":\"CMGHG01\"," +
                    "\"accountcode\":\"CODE\",\"symbol\":\"NIFTY\",\"bfq\":\"4\",\"netq\":\"2\",\"cfq\":\"2\",\"expirydate\":\"11JAN2022\",\"strikeprice\":\"PE\"},{\"srno\":1,\"userid\":\"CMGHG01\",\"accountcode\":\"CODE\"," +
                    "\"symbol\":\"NIFTY\",\"bfq\":\"1\",\"netq\":\"3\",\"cfq\":\"6\",\"expirydate\":\"11JAN2022\",\"strikeprice\":\"CE\"},{\"srno\":2,\"userid\":\"USER1\",\"accountcode\":\"COFFEE\",\"symbol\":\"SGX\",\"bfq\":\"4\",\"netq\":\"3\",\"cfq\":\"9\",\"expirydate\":\"24MAR2022\",\"strikeprice\":\"CE\"}," +
                    "{\"srno\":3,\"userid\":\"USER2\",\"accountcode\":\"TEA\",\"symbol\":\"NIFTY\",\"bfq\":\"5\",\"netq\":\"2\",\"cfq\":\"1\",\"expirydate\":\"3JUL2022\",\"strikeprice\":\"PE\"},{\"srno\":4,\"userid\":\"USER2\",\"accountcode\":\"CODEABC\"," +
                    "\"symbol\":\"NIFTY\",\"bfq\":\"2\",\"netq\":\"1\",\"cfq\":\"1\",\"expirydate\":\"19JUL2022\",\"strikeprice\":\"CE\"},{\"srno\":7,\"userid\":\"USER1\",\"accountcode\":\"STAR\",\"symbol\":\"NIFTY\",\"bfq\":\"4\",\"netq\":\"2\",\"cfq\":\"2\",\"expirydate\":\"19JUL2024\",\"strikeprice\":\"PE\"}," +
                    "{\"srno\":7,\"userid\":\"USER2\",\"accountcode\":\"CODEVITA\",\"symbol\":\"NIFTY\",\"bfq\":\"4\",\"netq\":\"2\",\"cfq\":\"2\",\"expirydate\":\"19JUL2024\",\"strikeprice\":\"CE\"}," +
                    "{\"srno\":2,\"userid\":\"USER1\",\"accountcode\":\"TEA\",\"symbol\":\"NIFTY\",\"bfq\":\"4\",\"netq\":\"2\",\"cfq\":\"2\",\"expirydate\":\"24MAR2022\",\"strikeprice\":\"CE\"}," +
                    "{\"srno\":3,\"userid\":\"USER2\",\"accountcode\":\"CODE\",\"symbol\":\"SGX\",\"bfq\":\"4\",\"netq\":\"2\",\"cfq\":\"2\",\"expirydate\":\"3JUL2022\",\"strikeprice\":\"PE\"}," +
                    "{\"srno\":4,\"userid\":\"USER2\",\"accountcode\":\"CODE\",\"symbol\":\"NIFTY\",\"bfq\":\"4\",\"netq\":\"2\",\"cfq\":\"2\",\"expirydate\":\"19JUL2022\",\"strikeprice\":\"CE\"}," +
                    "{\"srno\":7,\"userid\":\"USER1\",\"accountcode\":\"STAR\",\"symbol\":\"NIFTY\",\"bfq\":\"4\",\"netq\":\"2\",\"cfq\":\"2\",\"expirydate\":\"19JUL2024\",\"strikeprice\":\"PE\"}," +
                    "{\"srno\":1,\"userid\":\"CMGHG01\",\"accountcode\":\"COFFEE\",\"symbol\":\"SGX\",\"bfq\":\"4\",\"netq\":\"2\",\"cfq\":\"2\",\"expirydate\":\"11JAN2022\",\"strikeprice\":\"CE\"}]");



            JSONArray sourceArray = data;
            JSONArray destinationArray = data1;
            System.out.println("fffffffffffff " +data );
            System.out.println("ssssssssssssss " +data1 );

            for (int i = 0; i < sourceArray.length(); i++) {
                destinationArray.put(sourceArray.getJSONObject(i));
            }

            String s3 = destinationArray.toString();
            System.out.println("scscs " +s3);








            formatData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void formatData(JSONArray data) {
        summeryModels = new ArrayList<>();
        Toast.makeText(this, ""+data.length(), Toast.LENGTH_SHORT).show();

        try {
            for (int i=0; i<data.length(); i++) {
                String userId = data.getJSONObject(i).getString("userid");
                int index = chekId(userId);
                if (index!=-1){
                    //in this case summery model is already in list
                    //we have to add single trade object to that list

                    JSONObject singleRow = data.getJSONObject(i);
                    TradeModel trade = new TradeModel();

                    trade.sr = singleRow.getString("srno");
                    trade.userId = singleRow.getString("userid");
                    trade.accountCode = singleRow.getString("accountcode");
                    trade.expiry = singleRow.getString("expirydate");
                    trade.symbol = singleRow.getString("symbol");

                    //get existing model from list
                    TradeSummeryModel tmp_model = summeryModels.get(index);

                    //add trade in tmp model
                    tmp_model.tradeList.add(trade);

                    //replace tmp model with old model in list
                    summeryModels.set(index,tmp_model);

                }else{
                    //single row of data
                    JSONObject singleRow = data.getJSONObject(i);

                    //trade summery model
                    TradeSummeryModel full_summery = new TradeSummeryModel();

                    //trade model
                    TradeModel trade = new TradeModel();
                    //fill data in trade model
                    trade.sr = singleRow.getString("srno");
                    trade.userId = singleRow.getString("userid");
                    trade.accountCode = singleRow.getString("accountcode");
                    trade.expiry = singleRow.getString("expirydate");
                    trade.symbol = singleRow.getString("symbol");

                    full_summery.userId = singleRow.getString("userid");
                    full_summery.tradeList = new ArrayList<>();
                    full_summery.tradeList.add(trade);

                    summeryModels.add(full_summery);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initRecyclers();
            }
        },1000);

    }



    private int chekId(String userId) {
        for (int i = 0; i < summeryModels.size(); i++) {
            if (summeryModels.get(i).userId.equalsIgnoreCase(userId))
                return i;
        }

        return -1;
    }

    private void initRecyclers() {
        //after purifying data let's show it
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);

        MainAdapter adapter = new MainAdapter(this,summeryModels);

        list.setLayoutManager(manager);
        list.setAdapter(adapter);

    }
}