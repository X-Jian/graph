package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import java.awt.GridLayout;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {

        Map<String, Integer>[] listTower = new HashMap[65];
        Map<String, Integer>[] listCard = new HashMap[65];
        List<Integer>[] stars = new ArrayList[65];

        int[] rewardItem = new int[8];

        Map<String, Integer> storeItem = new HashMap<>();
        List<String> card_type1 = new ArrayList<String>();
        List<String> card_type2 = new ArrayList<String>();
        Map<String, List<Integer>> upgrade1 = new HashMap<>();
        Map<String, List<Integer>> upgrade2 = new HashMap<>();
        Set<String> users = new HashSet<String>();
        Set<String> ids = new HashSet<String>();
        int n = 0;
        List<File> list = new ArrayList<File>();
        int[] res = new int[65];
        int count = 0;
        int game_start = 0;
        int level_complete = 0;
        Map<Integer, List<Integer>> level_ratio = new HashMap<>();

        String test = "\"item_id\":\"";
        String[] hahaha = test.split(":");
        System.out.println(Arrays.toString(hahaha));
        System.out.println(test.length());

        for(int i = 0; i < listTower.length; i++){
            listTower[i] = new HashMap<String, Integer>();
        }
        for(int i = 0; i < listCard.length; i++){
            listCard[i] = new HashMap<String, Integer>();
        }
        for(int i = 0; i < stars.length; i++){
            stars[i] = new ArrayList<Integer>();
        }



        File file1 = new File("/Users/xujian/Documents/spurpunk-record/Data10/part-4db6471b-699f-4652-b1b3-b6643e29f4a1/part-4db6471b-699f-4652-b1b3-b6643e29f4a1");
        File file2 = new File("/Users/xujian/Documents/spurpunk-record/Data10/part-9070a782-f05d-4d41-b2e6-82a1ea049cff/part-9070a782-f05d-4d41-b2e6-82a1ea049cff");
        File file3 = new File("/Users/xujian/Documents/spurpunk-record/Data10/part-9113615d-e22e-4940-9166-712f5e1c4767/part-9113615d-e22e-4940-9166-712f5e1c4767");
        File file4 = new File("/Users/xujian/Documents/spurpunk-record/Data10/part-e3d38bf9-350d-4a6a-bbe5-76ff6b6b4e16/part-e3d38bf9-350d-4a6a-bbe5-76ff6b6b4e16");
        File file5 = new File("/Users/xujian/Documents/spurpunk-record/data109/part-e45b0b37-0122-4fb1-995a-3b31a96db71f/part-e45b0b37-0122-4fb1-995a-3b31a96db71f");
        File file6 = new File("/Users/xujian/Documents/spurpunk-record/data109/part-ff027790-07b0-4953-8bf7-3d821f60cda5/part-ff027790-07b0-4953-8bf7-3d821f60cda5");
        File file7 = new File("/Users/xujian/Documents/spurpunk-record/Data9/part-5910cce5-8f12-4f20-a675-1ca516335ed0/part-5910cce5-8f12-4f20-a675-1ca516335ed0");
        File file8 = new File("/Users/xujian/Documents/spurpunk-record/Data9/part-bfea7af6-5e93-43a0-8979-367a4038b534/part-bfea7af6-5e93-43a0-8979-367a4038b534");
        list.add(file1);
        list.add(file2);
        list.add(file3);
        list.add(file4);
//        list.add(file5);
//        list.add(file6);
//        list.add(file7);
//        list.add(file8);

        for(File file: list){
            if(!file.exists())
            {
                System.out.println("file does not exist");
                return;
            }
            Scanner scanner=new Scanner(file);
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] lineWords = line.split(",");
                String type = lineWords[0];
                String type_value = type.substring(8);
//                System.out.println(Arrays.toString(lineWords));

                /****************************** graph 1 data***********************************************/
                String curID = lineWords[3].substring(12);
                String curUser = lineWords[2].substring(9);
                ids.add(curID);
                users.add(curUser);
                if(type_value.equals("\"level_complete\"")){
                    level_complete++;
                }

                /****************************** graph 2 data***********************************************/
                if(type_value.equals("\"level_start\"")){
                    for(int i = 1; i < lineWords.length; i++){
                        if(lineWords[i].length() >= 30 && lineWords[i].substring(17, 30).equals("\"level_index\"")){
                            String temp = lineWords[i].substring(31,lineWords[i].length() - 1).replaceAll("\\p{Punct}","");
                            int index = Integer.parseInt(temp);
                            if(level_ratio.containsKey(index)){
                                level_ratio.get(index).set(0, level_ratio.get(index).get(0) + 1);
                            } else {
                                List<Integer> newList = new ArrayList<Integer>();
                                newList.add(1);
                                newList.add(0);
                                level_ratio.put(index, newList);
                            }
                        }
                    }
                }
                if(type_value.equals("\"level_complete\"")){
                    for(int i = 1; i < lineWords.length; i++){
                        if(lineWords[i].length() >= 30 && lineWords[i].substring(17, 30).equals("\"level_index\"")){
                            String temp = lineWords[i].substring(31,lineWords[i].length() - 1).replaceAll("\\p{Punct}","");
                            int index = Integer.parseInt(temp);
                            if(level_ratio.containsKey(index)){
                                level_ratio.get(index).set(1, level_ratio.get(index).get(1) + 1);
                            } else {
                                List<Integer> newList = new ArrayList<Integer>();
                                newList.add(0);
                                newList.add(1);
                                level_ratio.put(index, newList);
                            }
                        }
                    }
                }

                /****************************** graph 3 & 4 data***********************************************/
                if(type_value.equals("\"level_up\"")){
                    for(int i = 1; i < lineWords.length; i++){
                        if(lineWords[i].length() >= 33 && lineWords[i].substring(17,33).equals("\"new_level_name\"")){
                            String cardName = lineWords[i].substring(35,lineWords[i].length() - 1);
                            String[] temp = lineWords[i + 1].split(":");
                            String upGrade = temp[0].replaceAll("\\p{Punct}","");
                            String max_level = temp[1].replaceAll("\\p{Punct}","");
                            if(upGrade.equals("1")){
                                card_type1.add(cardName);
                                if(upgrade1.containsKey(cardName)){
                                    upgrade1.get(cardName).add(Integer.parseInt(max_level));
                                } else {
                                    List<Integer> tempList = new ArrayList<Integer>();
                                    tempList.add(Integer.parseInt(max_level));
                                    upgrade1.put(cardName, tempList);
                                }
                            } else if(upGrade.equals("2")){
                                card_type2.add(cardName);
                                if(upgrade2.containsKey(cardName)){
                                    upgrade2.get(cardName).add(Integer.parseInt(max_level));
                                } else {
                                    List<Integer> tempList = new ArrayList<Integer>();
                                    tempList.add(Integer.parseInt(max_level));
                                    upgrade2.put(cardName, tempList);
                                }
                            }
                        }
                    }
                }

                /****************************** graph 5 data***********************************************/
                if(type_value.equals("\"item_acquired\"")){
                    for(int i = 1; i < lineWords.length; i++){
                        if(lineWords[i].length() >= 22 && lineWords[i].substring(22).equals("\"store\"")){
                            String item = lineWords[i + 2].substring(11, lineWords[i + 2].length() - 2);
                            if(storeItem.containsKey(item)){
                                storeItem.put(item, storeItem.get(item) + 1);
                            } else {
                                storeItem.put(item, 1);
                            }
                        }
                    }
                }

                /****************************** graph 6 & 7 data***********************************************/
                if(type_value.equals("\"item_spent\"")){
                    for(int i = 1; i < lineWords.length; i++){
                        if(lineWords[i].length() >= 22 && lineWords[i].substring(22).equals("\"Tower\"")){
                            String levelString = lineWords[i - 1].substring(27, lineWords[i-1].length() - 1);
                            int level = (int)(Double.parseDouble(levelString));

                            if(lineWords[i + 2].length() >= 11 && lineWords[i + 2].substring(0, 9).equals("\"item_id\"")){
                                String tower = lineWords[i + 2].substring(11, lineWords[i + 2].length() - 2);
                                if(listTower[level].containsKey(tower)){
                                    listTower[level].put(tower, listTower[level].get(tower) + 1);
                                } else {
                                    listTower[level].put(tower, 1);
                                }
                            }
                        } else if(lineWords[i].length() >= 22 && lineWords[i].substring(22).equals("\"Card\"")){
                            String levelString = lineWords[i - 1].substring(27, lineWords[i-1].length() - 1);
                            int level = (int)(Double.parseDouble(levelString));

                            if(lineWords[i + 2].length() >= 11 && lineWords[i + 2].substring(0, 9).equals("\"item_id\"")){
                                String tower = lineWords[i + 2].substring(11, lineWords[i + 2].length() - 2);
                                if(listCard[level].containsKey(tower)){
                                    listCard[level].put(tower, listCard[level].get(tower) + 1);
                                } else {
                                    listCard[level].put(tower, 1);
                                }
                            }
                        }
                    }
                }
                /****************************** graph 8 data***********************************************/
                if(type_value.equals("\"item_acquired\"")){
                    for(int i = 1; i < lineWords.length; i++){
                        if(lineWords[i].length() >= 22 && lineWords[i].substring(22).equals("\"Reward\"")){
                            String item_idTemp = lineWords[i + 2].substring(11, lineWords[i + 2].length() - 2);
                            int item_id = Integer.parseInt(item_idTemp);
                            rewardItem[item_id]++;
                        }
                    }
                }

                /****************************** graph 9 data***********************************************/
                if(type_value.equals("\"level_complete\"")){
                    for(int i = 1; i < lineWords.length; i++){
                        if(lineWords[i].length() >= 30 && lineWords[i].substring(17, 30).equals("\"level_index\"")){
                            String temp = lineWords[i].substring(31,lineWords[i].length() - 1).replaceAll("\\p{Punct}","");
                            int index = Integer.parseInt(temp);
                            String starsTemp = lineWords[i + 1].substring(9,lineWords[i + 1].length() - 1).replaceAll("\\p{Punct}","");
                            int starsNum = Integer.parseInt(starsTemp);
//                            System.out.println(index);
                            stars[index].add(starsNum);
                        }
                    }
                }
            }
        }

        /****************************** graph 1 graph ***********************************************/
        int[] graph1 = new int[3];
        graph1[0] = users.size();
        graph1[1] = ids.size();
        graph1[2] = level_complete;

        JFrame frame=new JFrame("Number of passes for each level");
        frame.setLayout(new GridLayout(2,2,10,10));
        frame.add(new BarChart(graph1).getChartPanel());      //添加柱形图
        frame.setBounds(50, 50, 800, 600);
        frame.setVisible(true);

        /****************************** graph 2 graph ***********************************************/
//        int[] data_start = new int[65];
//        int[] data_complete = new int[65];
//        for (Map.Entry<Integer, List<Integer>> entry : level_ratio.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
////            double ratioTemp = (entry.getValue().get(1) * 1.0) / (entry.getValue().get(0));
////            BigDecimal bg = new BigDecimal(ratioTemp);
////            double ratio = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
////            System.out.println(ratio);
//            data_start[entry.getKey()] = entry.getValue().get(0);
//            data_complete[entry.getKey()] = entry.getValue().get(1);
//        }
//        JFrame frame2 = new JFrame("Number of passes for each level");
//        frame2.setLayout(new GridLayout(1,1,30,30));
//        frame2.add(new BarChart2(data_start, data_complete).getChartPanel());      //添加柱形图
//        frame2.setBounds(50, 50, 800, 600);
//        frame2.setVisible(true);


        /****************************** graph 3 & 4 graph ***********************************************/
//        String[] cardName1 = new String[upgrade1.size() + 1];
//        double[] average1 = new double[upgrade1.size() + 1];
//        int i1 = 1;
//        int total1 = 0;
//        int count1 = 0;
//        for (Map.Entry<String, List<Integer>> entry1 : upgrade1.entrySet()) {
//            cardName1[i1] = entry1.getKey();
//            for(int value : entry1.getValue()){
//                total1 += value;
//                count1++;
//            }
//            double aveTemp = (total1 * 1.0) / count1;
//            BigDecimal bg = new BigDecimal(aveTemp);
//            double ave = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//            average1[i1] = ave;
//            i1++;
//        }
//        JFrame frame2 = new JFrame("Number of passes for each level");
//        frame2.setLayout(new GridLayout(1,1,30,30));
//        frame2.add(new BarChart3(cardName1, average1).getChartPanel());      //添加柱形图
//        frame2.setBounds(50, 50, 800, 600);
//        frame2.setVisible(true);
//
//
//        String[] cardName2 = new String[upgrade2.size() + 1];
//        double[] average2 = new double[upgrade2.size() + 1];
//        int i2 = 1;
//        int total2 = 0;
//        int count2 = 0;
//        for (Map.Entry<String, List<Integer>> entry2 : upgrade2.entrySet()) {
//            cardName2[i2] = entry2.getKey();
//            for(int value : entry2.getValue()){
//                total2 += value;
//                count2++;
//            }
//            double aveTemp = (total2 * 1.0) / count2;
//            BigDecimal bg = new BigDecimal(aveTemp);
//            double ave = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//            average2[i2] = ave;
//            i2++;
//        }
//        JFrame frame4 = new JFrame("Number of passes for each level");
//        frame4.setLayout(new GridLayout(1,1,30,30));
//        frame4.add(new BarChart4(cardName2, average2).getChartPanel());      //添加柱形图
//        frame4.setBounds(50, 50, 800, 600);
//        frame4.setVisible(true);


        /****************************** graph 5 graph ***********************************************/
//        String[] store = new String[storeItem.size() + 1];
//        int[] number = new int[storeItem.size() + 1];
//        int i = 1;
//        for (Map.Entry<String, Integer> entry : storeItem.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//            store[i] = entry.getKey();
//            number[i] = entry.getValue();
//            i++;
//        }
//        JFrame frame2 = new JFrame("Number of passes for each level");
//        frame2.setLayout(new GridLayout(1,1,30,30));
//        frame2.add(new BarChart5(store, number).getChartPanel());      //添加柱形图
//        frame2.setBounds(50, 50, 800, 600);
//        frame2.setVisible(true);

        /****************************** graph 6 graph ***********************************************/
//        List<List<String>> tower = new ArrayList<List<String>>();
//        List<List<Integer>> number = new ArrayList<List<Integer>>();
//
//        for(int i = 1; i < listTower.length; i++){
//            System.out.println("level" + i + "" + listTower[i]);
//        }
//
//        for(int i = 1; i < listTower.length; i++){
//            List<String> curLayerTower = new ArrayList<String>();
//            List<Integer> curLayerNumber = new ArrayList<Integer>();
//            for (Map.Entry<String, Integer> entry : listTower[i].entrySet()) {
//                curLayerTower.add(entry.getKey());
//                curLayerNumber.add(entry.getValue());
//            }
//            tower.add(curLayerTower);
//            number.add(curLayerNumber);
//        }
//
//        JFrame frame2 = new JFrame("Number of passes for each level");
//        frame2.setLayout(new GridLayout(1,1,1,1));
//        frame2.add(new BarChart6(tower, number).getChartPanel());      //添加柱形图
//        frame2.setBounds(50, 50, 800, 600);
//        frame2.setVisible(true);

        /****************************** graph 7 graph ***********************************************/
//        List<List<String>> tower = new ArrayList<List<String>>();
//        List<List<Integer>> number = new ArrayList<List<Integer>>();
//
//        for(int i = 1; i < listCard.length; i++){
//            System.out.println("level" + i + "" + listCard[i]);
//        }
//
//        for(int i = 1; i < listCard.length; i++){
//            List<String> curLayerTower = new ArrayList<String>();
//            List<Integer> curLayerNumber = new ArrayList<Integer>();
//            Queue<Map.Entry<String, Integer>> pq = new PriorityQueue<Map.Entry<String, Integer>>(5, new Comparator<Map.Entry<String, Integer>>() {
//                @Override
//                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                    return o1.getValue() - o2.getValue();
//                }
//            });
//            for (Map.Entry<String, Integer> entry : listCard[i].entrySet()) {
//                pq.add(entry);
//                if(pq.size() > 5){
//                    pq.poll();
//                }
//            }
//            while(!pq.isEmpty()){
//                Map.Entry<String, Integer> tempEntry = pq.poll();
//                curLayerTower.add(tempEntry.getKey());
//                curLayerNumber.add(tempEntry.getValue());
//            }
//            tower.add(curLayerTower);
//            number.add(curLayerNumber);
//        }
//
//        System.out.println(number);
//
//        JFrame frame2 = new JFrame("Number of passes for each level");
//        frame2.setLayout(new GridLayout(1,1,1,1));
//        frame2.add(new BarChart7(tower, number).getChartPanel());      //添加柱形图
//        frame2.setBounds(50, 50, 1500, 600);
//        frame2.setVisible(true);

        /****************************** graph 8 graph ***********************************************/
//        JFrame frame2 = new JFrame("Reward Collection Rates");
//        frame2.setLayout(new GridLayout(1,1,1,1));
//        frame2.add(new BarChart8(rewardItem).getChartPanel());      //添加柱形图
//        frame2.setBounds(50, 50, 1500, 600);
//        frame2.setVisible(true);

        /****************************** graph 9 graph ***********************************************/
//        double[] starsAve = new double[65];
//        for(int i = 0; i < stars.length; i++){
//            double total = 0;
//            double num = 0;
//            for(Integer cur : stars[i]){
//                total += cur;
//                num++;
//            }
//            if(num != 0){
//                double aveTemp = total / num;
//                BigDecimal bg = new BigDecimal(aveTemp);
//                double ave = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//                starsAve[i] = ave;
//            }
//        }
//        JFrame frame2 = new JFrame("Stars Won per Pass");
//        frame2.setLayout(new GridLayout(1,1,1,1));
//        frame2.add(new BarChart9(starsAve).getChartPanel());      //添加柱形图
//        frame2.setBounds(50, 50, 1500, 600);
//        frame2.setVisible(true);
    }
}

class BarChart9 {
    ChartPanel frame1;
    public BarChart9(double[] data){
        CategoryDataset dataset = getDataSet(data);
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Stars Won per Pass", // 图表标题
                "Level Index", // 目录轴的显示标签
                "Avg Stars", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                false,           // 是否显示图例(对于简单的柱状图必须是false)
                false,          // 是否生成工具
                false           // 是否生成URL链接
        );
        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis();
//        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
//        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //底部坐标轴
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
//        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
//        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
//        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体


        frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame

    }
    private static CategoryDataset getDataSet(double[] data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i = 0; i < data.length; i++){
            if(i == 0 || data[i] == 0){
                continue;
            }
            dataset.addValue(data[i],"", "" + i);
        }
        return dataset;
    }
    public ChartPanel getChartPanel(){
        return frame1;
    }
}

class BarChart8 {
    ChartPanel frame1;
    public BarChart8(int[] data){
        CategoryDataset dataset = getDataSet(data);
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Reward Collection Rates", // 图表标题
                "Day Number(1-7)", // 目录轴的显示标签
                "Total Times", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                false,           // 是否显示图例(对于简单的柱状图必须是false)
                false,          // 是否生成工具
                false           // 是否生成URL链接
        );
        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis();
//        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
//        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //底部坐标轴
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
//        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
//        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
//        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体
        frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame
    }
    private static CategoryDataset getDataSet(int[] data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i = 0; i < data.length; i++){
            if(i == 0){
                continue;
            }
            dataset.addValue(data[i],"", "day " + i + " reward");
        }
        return dataset;
    }
    public ChartPanel getChartPanel(){
        return frame1;
    }
}

class BarChart7 {
    ChartPanel frame1;
    public BarChart7(List<List<String>> data1, List<List<Integer>> data2){
        CategoryDataset dataset = getDataSet(data1, data2);
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Top 5 Card Usage Per Level", // 图表标题
                "Level", // 目录轴的显示标签
                "Count", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true,           // 是否显示图例(对于简单的柱状图必须是false)
                false,          // 是否生成工具
                false           // 是否生成URL链接
        );
        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis();
//        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
//        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //底部坐标轴
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
//        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
//        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
//        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体


        frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame

    }
    private static CategoryDataset getDataSet(List<List<String>> data1, List<List<Integer>> data2) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i = 0; i < data1.size(); i++){
            for(int j = 0; j < data2.get(i).size(); j++){
                dataset.addValue(data2.get(i).get(j), data1.get(i).get(j) + "     ", i+1 + "  ");
            }
        }
//        for(int i = 0; i < 30; i++){
//            for(int j = 0; j < data2.get(i).size(); j++){
//                dataset.addValue(data2.get(i).get(j), data1.get(i).get(j) + "     ", i+1 + "  ");
//            }
//        }
//        for(int i = 30; i < data1.size(); i++){
//            for(int j = 0; j < data1.get(i).size(); j++){
//                dataset.addValue(data2.get(i).get(j), data1.get(i).get(j) + "     ", i+1 + "  ");
//            }
//        }
        return dataset;
    }
    public ChartPanel getChartPanel(){
        return frame1;
    }
}


class BarChart6 {
    ChartPanel frame1;
    public BarChart6(List<List<String>> data1, List<List<Integer>> data2){
        CategoryDataset dataset = getDataSet(data1, data2);
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Tower Usage Per Level", // 图表标题
                "Level", // 目录轴的显示标签
                "Count", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true,           // 是否显示图例(对于简单的柱状图必须是false)
                false,          // 是否生成工具
                false           // 是否生成URL链接
        );
        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis();
//        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
//        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //底部坐标轴
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
//        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
//        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
//        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体


        frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame

    }
    private static CategoryDataset getDataSet(List<List<String>> data1, List<List<Integer>> data2) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i = 0; i < data1.size(); i++){
            for(int j = 0; j < data1.get(i).size(); j++){
                dataset.addValue(data2.get(i).get(j), data1.get(i).get(j) + "     ", i+1 + "  ");
            }
        }
//        for(int i = 0; i < 30; i++){
//            for(int j = 0; j < data1.get(i).size(); j++){
//                dataset.addValue(data2.get(i).get(j), data1.get(i).get(j) + "     ", i+1 + "  ");
//            }
//        }
//        for(int i = 30; i < data1.size(); i++){
//            for(int j = 0; j < data1.get(i).size(); j++){
//                dataset.addValue(data2.get(i).get(j), data1.get(i).get(j) + "     ", i+1 + "  ");
//            }
//        }
        return dataset;
    }
    public ChartPanel getChartPanel(){
        return frame1;
    }
}

class BarChart5 {
    ChartPanel frame1;
    public BarChart5(String[] data1, int[] data2){
        CategoryDataset dataset = getDataSet(data1, data2);
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Spell Purchased", // 图表标题
                "Spell Type", // 目录轴的显示标签
                "Count", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                false,           // 是否显示图例(对于简单的柱状图必须是false)
                false,          // 是否生成工具
                false           // 是否生成URL链接
        );
        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis();
//        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
//        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //底部坐标轴
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
//        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
//        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
//        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体

        //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

        frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame

    }
    private static CategoryDataset getDataSet(String[] data1, int[] data2) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i = 1; i < data1.length; i++){
            dataset.addValue(data2[i],"",data1[i]);
        }
        return dataset;
    }
    public ChartPanel getChartPanel(){
        return frame1;
    }
}


class BarChart4 {
    ChartPanel frame1;
    public BarChart4(String[] data1, double[] data2){
        CategoryDataset dataset = getDataSet(data1, data2);
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Avg Level of Card Upgrade (2)", // 图表标题
                "Card Type", // 目录轴的显示标签
                "Avg Level", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true,           // 是否显示图例(对于简单的柱状图必须是false)
                false,          // 是否生成工具
                false           // 是否生成URL链接
        );
        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis();
//        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
//        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //底部坐标轴
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
//        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
//        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
//        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体

        //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

        frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame

    }
    private static CategoryDataset getDataSet(String[] data1, double[] data2) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i = 1; i < data1.length; i++){
            dataset.addValue(data2[i],"   " + data1[i] + "  ",data1[i]);
        }
        return dataset;
    }
    public ChartPanel getChartPanel(){
        return frame1;
    }
}

class BarChart3 {
    ChartPanel frame1;
    public BarChart3(String[] data1, double[] data2){
        CategoryDataset dataset = getDataSet(data1, data2);
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Avg Level of Card Upgrade (1)", // 图表标题
                "Card Type", // 目录轴的显示标签
                "Avg Level", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true,           // 是否显示图例(对于简单的柱状图必须是false)
                false,          // 是否生成工具
                false           // 是否生成URL链接
        );
        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis();
//        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
//        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //底部坐标轴
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
//        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
//        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
//        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体

        //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

        frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame

    }
    private static CategoryDataset getDataSet(String[] data1, double[] data2) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i = 1; i < data1.length; i++){
            dataset.addValue(data2[i],"   " + data1[i] + "  ",data1[i]);
        }
        return dataset;
    }
    public ChartPanel getChartPanel(){
        return frame1;
    }
}

class BarChart2 {
    ChartPanel frame1;
    public BarChart2(int[] data1, int[] data2){
        CategoryDataset dataset = getDataSet(data1, data2);
        JFreeChart chart = ChartFactory.createBarChart3D(
                "Level Start vs Complete", // 图表标题
                "Level_Index", // 目录轴的显示标签
                "Count", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true,           // 是否显示图例(对于简单的柱状图必须是false)
                false,          // 是否生成工具
                false           // 是否生成URL链接
        );
        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis();
//        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
//        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //底部坐标轴
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
//        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
//        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
//        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体

        //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

        frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame

    }
    private static CategoryDataset getDataSet(int[] data1, int[] data2) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i = 1; i < data1.length; i++){
            if(data1[i] == 0){
                continue;
            }
            dataset.addValue(data1[i],"start", "" + i);
            dataset.addValue(data2[i],"complete", "" + i);
        }
//        for(int i = 1; i < 30; i++){
//            if(data1[i] == 0){
//                continue;
//            }
//            dataset.addValue(data1[i],"start", "" + i);
//            dataset.addValue(data2[i],"complete", "" + i);
//        }
//        for(int i = 30; i < data1.length; i++){
//            if(data1[i] == 0){
//                continue;
//            }
//            dataset.addValue(data1[i],"start", "" + i);
//            dataset.addValue(data2[i],"complete", "" + i);
//        }
        return dataset;
    }
    public ChartPanel getChartPanel(){
        return frame1;
    }
}


class BarChart {
    ChartPanel frame1;
    public BarChart(int[] data){
        CategoryDataset dataset = getDataSet(data);
        JFreeChart chart = ChartFactory.createBarChart3D(
                "User/Session/Level Count", // 图表标题
                "", // 目录轴的显示标签
                "Count", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                false,           // 是否显示图例(对于简单的柱状图必须是false)
                false,          // 是否生成工具
                false           // 是否生成URL链接
        );
        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis();
//        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
//        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //底部坐标轴
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
//        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
//        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
//        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体

        //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

        frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame

    }
    private static CategoryDataset getDataSet(int[] data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(data[0],"", "Users");
        dataset.addValue(data[1],"", "Sessions");
        dataset.addValue(data[2],"", "level_Passes");
        return dataset;
    }
    public ChartPanel getChartPanel(){
        return frame1;
    }
}
