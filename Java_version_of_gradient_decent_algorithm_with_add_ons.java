/* class name MUST BE THE SAME AS THE FILE NAME */
/*Java is capital-sensitive*/
import java.lang.Math; /*imports java's math library*/
import java.util.Scanner; //imports Scanner library
import java.util.Random; //imports random library
import java.util.Arrays; //lets you use arrays easily
import java.lang.Integer;


class Java_version_of_gradient_decent_algorithm_with_add_ons {



  
  public static double[][] init(int layers[], double[] initializing_range)
  {
   int weights_amount=0;
   double[][] outputs_of_init={{},{},{},{}};
   double[] nodes_counted_in_each_layer={0, layers[0]};
    for (int i=0; i<layers.length-1; i++)
    {
    weights_amount+=layers[i]*layers[i+1];
    }
    for (int i1=0; i1<weights_amount; i1++)
    {
      outputs_of_init[0]=Arrays.copyOf(outputs_of_init[0], outputs_of_init[0].length+1);
      outputs_of_init[0][outputs_of_init[0].length-1]=((double)((Math.random() * (initializing_range[1] - initializing_range[0])) + initializing_range[0]));
    }

    int biases_amount=0;
    for (int i2=0; i2<layers.length-1; i2++)
    {
      biases_amount+=layers[i2+1];
    }
    for (int i3=0; i3<biases_amount; i3++)
    {
      outputs_of_init[1]=Arrays.copyOf(outputs_of_init[1], outputs_of_init[1].length+1);
      outputs_of_init[1][outputs_of_init[1].length-1]=((double)((Math.random() * (initializing_range[1] - initializing_range[0])) + initializing_range[0]));
    }

    outputs_of_init[2]=Arrays.copyOf(outputs_of_init[2], outputs_of_init[2].length+1);
    outputs_of_init[2][outputs_of_init[2].length-1]=weights_amount;
    outputs_of_init[2]=Arrays.copyOf(outputs_of_init[2], outputs_of_init[2].length+1);
    outputs_of_init[2][outputs_of_init[2].length-1]=biases_amount;

    int nodes_already_counted_num=0;
    for (int i4=1; i4<layers.length; i4++)
    {
      nodes_counted_in_each_layer=Arrays.copyOf(nodes_counted_in_each_layer, nodes_counted_in_each_layer.length+1);
      double layers_i4=layers[i4];
      nodes_already_counted_num+=layers[i4-1];
      nodes_counted_in_each_layer[nodes_counted_in_each_layer.length-1]=layers_i4+nodes_already_counted_num;
    }

    outputs_of_init[3]=Arrays.copyOf(outputs_of_init[3], outputs_of_init[3].length+1);
    outputs_of_init[3]=nodes_counted_in_each_layer;
    
    return (double[][])outputs_of_init;
  }


  public static double[][][][] run_network(double[] full_population_weights, double[] full_population_biases, double[] INPUTS, int[] layers, String activation_functions)
  {
    double[] running_network_weights=full_population_weights;
    double[] running_network_biases=full_population_biases;
    double[] node_firing_numbers={};
    double[][][] weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer={};
    double[] NODES_PROSCESSED=INPUTS;
    for (int i=0; i<INPUTS.length; i++)
    {
      node_firing_numbers=Arrays.copyOf(node_firing_numbers, node_firing_numbers.length+1);
      node_firing_numbers[node_firing_numbers.length-1]=INPUTS[i];
    }
    int computationoal_part_weights=0;
    int computationoal_part_biases=0;

    for (int i1=0; i1<layers.length-1;i1++)
    {
      double[] nodes_proscessing={};
      weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer=Arrays.copyOf(weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer, weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer.length+1);
      weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer[weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer.length-1]=new double[][] {};
      for (int i2=0; i2<layers[i1+1]; i2++)
      {
        double node_in_next_layer_VALUE=0;
        weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer[i1]=Arrays.copyOf(weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer[i1], weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer[i1].length+1);
        weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer[i1][weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer[i1].length-1]=new double[] {};
        for (int i3=0; i3<layers[i1]; i3++)
        {
          double edge_value=running_network_weights[computationoal_part_weights]*NODES_PROSCESSED[i3];
          weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer[i1][i2]=Arrays.copyOf(weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer[i1][i2], weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer[i1][i2].length+1);
          weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer[i1][i2][weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer[i1][i2].length-1]=running_network_weights[computationoal_part_weights];
          computationoal_part_weights++;
          node_in_next_layer_VALUE+=edge_value;
        }
      nodes_proscessing=Arrays.copyOf(nodes_proscessing, nodes_proscessing.length+1);
      nodes_proscessing[nodes_proscessing.length-1]=node_in_next_layer_VALUE+running_network_biases[computationoal_part_biases];
      node_firing_numbers=Arrays.copyOf(node_firing_numbers, node_firing_numbers.length+1);
      node_firing_numbers[node_firing_numbers.length-1]=node_in_next_layer_VALUE+running_network_biases[computationoal_part_biases];
      computationoal_part_biases++;
      }
      NODES_PROSCESSED=nodes_proscessing;
    }
    double[] outputs=NODES_PROSCESSED;

    activation_functions=activation_functions.toLowerCase();
    if (activation_functions.contains("sigmoid"))
    {
      for (int i4=0; i4<layers[layers.length-1]; i4++)
      {
        outputs[i4]=1/(1+(Math.pow(2.71828, -1*outputs[i4])));
      }
    }
    if ((activation_functions.contains("binary_step")) || activation_functions.contains("binarystep"))
    {
      for (int i5=0; i5<layers[layers.length-1]; i5++)
      {
        if (outputs[i5]>0)
        {
          outputs[i5]=1;
        }
        else
        {
          outputs[i5]=0;
        }
      }
    }
    if (activation_functions.contains("tanh") || activation_functions.contains("hyperbolic_tangent") || activation_functions.contains("hyperbolictangent"))
    {
      for (int i6=0; i6<layers[layers.length-1]; i6++)
      {
        outputs[i6]=((Math.pow(2.71828, outputs[i6]))-(Math.pow(2.71828, -1*outputs[i6])))/((Math.pow(2.71828, outputs[i6]))+(Math.pow(2.71828, -1*outputs[i6])));
      }
    }
    if (activation_functions.contains("softplus"))
    { 
      for (int i7=0; i7<layers[layers.length-1]; i7++)
      {
        outputs[i7]=Math.log(1+(Math.pow(2.71828, outputs[i7])));
      }
    }
    if (activation_functions.contains("gaussian") || activation_functions.contains("gaussian_function") || activation_functions.contains("gaussianfunction"))
    {
      for (int i8=0; i8<layers[layers.length-1]; i8++)
      {
        outputs[i8]=Math.pow(Math.pow(2.71828, 0-outputs[i8]), 2);
      }
    }

    double[][][][] all_function_outputs={{{outputs}},{{node_firing_numbers}},weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer};
    return all_function_outputs;
  }



  public static double[] find_costs(double[] predictions, double[] goals)
  {
    double[] costs={};
    for (int i=0; i<predictions.length; i++)
    {
      costs=Arrays.copyOf(costs, costs.length+1);
      costs[costs.length-1]=Math.pow(predictions[i]-goals[i], 2);
    }

    return costs;
  }



  public static double test(double[][][] test_data, int[] layers, String activation_functions, double[] full_population_weights, double[] full_population_biases)
  {
    double error=0;
    for (int i=0; i<test_data.length; i++)
    {
      for (int i1=0; i1<layers[layers.length-1]; i1++)
      {
        error+=Math.pow(run_network(full_population_weights, full_population_biases, test_data[i][0], layers, activation_functions)[0][0][0][i1]-test_data[i][1][i1], 2);
      }
    }

    double average_error=error/test_data.length;
    return average_error;
  }

  public static double[][][][] train_test_data_split(double[][][] all_data, double[] ratios_of_data)
  {
    double[][][] training_data={};
    double[][][] testing_data={};
    int[] testing_data_IDs={};
    String testing_data_ID_STRING=" ";
    int appended_into_testing_data_id_string=0;
    while (appended_into_testing_data_id_string+1<all_data.length*ratios_of_data[0])
    {
      int random_ID=(int)Math.round(Math.random()*(all_data.length-1));
      if (!testing_data_ID_STRING.contains(" "+Integer.toString(random_ID)+" "))
      {
      testing_data_ID_STRING+=Integer.toString(random_ID)+" ";
      testing_data=Arrays.copyOf(testing_data, testing_data.length+1);
      testing_data[testing_data.length-1]=all_data[random_ID];
      appended_into_testing_data_id_string+=1;
      }
    }
    testing_data_ID_STRING+=" ";
    for (int i=0; i<all_data.length; i++)
    {
      if (!testing_data_ID_STRING.contains(" "+Integer.toString(i)+" "))
      {
        training_data=Arrays.copyOf(training_data, training_data.length+1);
        training_data[training_data.length-1]=all_data[i];
     }
    }

    double[][][][] function_outputs={training_data, testing_data};
    return function_outputs;
  }



  public static void main(String[] args) 
  {
    int[] LAYERS_BEING_USED={1,2,5};
    double[] INITIALIZING_RANGE={-3,3};
    double[][][] data={{{5},{1,2,3,4,5}},{{6},{2,3,4,5,6}}};
    double[][] all_outputs_of_init=init(LAYERS_BEING_USED, INITIALIZING_RANGE);
    double[] ratios_of_data={0,1};

    double[] full_population_weights=all_outputs_of_init[0];
    double[] full_population_biases=all_outputs_of_init[1];
    double weights_amount=all_outputs_of_init[2][0];
    double biases_amount=all_outputs_of_init[2][1];
    double[] nodes_counted_in_each_layer=all_outputs_of_init[3];

    double[] inputs={2.25};
    String activation_functions="";
    double[][][][] run_network_pagacked_outputs=run_network(full_population_weights, full_population_biases, inputs, LAYERS_BEING_USED, activation_functions);
    double[][][] weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer=run_network_pagacked_outputs[2];
    double[] outputs=run_network_pagacked_outputs[0][0][0];
    double[] node_firing_numbers=run_network_pagacked_outputs[1][0][0];
    System.out.println(Arrays.toString(outputs));
    //If you get an NaN as an output, just make the initializing range smaller.
    //Remember to code the training testing data split function, and that you've already coded the find_cost and test functions.    double[] ratios_of_data={0, 1}; //this is only an approximation... YOU CAN DO 0,1!



    double[][][][] train_test_data_split_outputs=train_test_data_split(data, ratios_of_data);
    double[][][] training_data=train_test_data_split_outputs[0];
    double[][][] testing_data=train_test_data_split_outputs[1];
  }

}

//Remember to go into https://github.com/Algorithmic-TITAN/Java_version_of_gradient_decent_algorithm_with_add_ons and drag and drop the file from file explorer (in folder src) into the repository to update the git repository.