/* class name MUST BE THE SAME AS THE FILE NAME */
/*Java is capital-sensitive*/
import java.lang.Math; /*imports java's math library*/
import java.util.Scanner; //imports Scanner library
import java.util.Random; //imports random library
import java.util.Arrays; //lets you use arrays easily


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


  public static double[][][][] run_network(double[] full_population_weights, double[] full_population_biases, double[] INPUTS, int[] layers)
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

    double[][][][] all_function_outputs={{{outputs}},{{node_firing_numbers}},weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer};
    return all_function_outputs;
  }



  public static void main(String[] args) 
  {
    int[] LAYERS_BEING_USED={1,2,5};
    double[] INITIALIZING_RANGE={-3,3};
    double[][] all_outputs_of_init=init(LAYERS_BEING_USED, INITIALIZING_RANGE);

    double[] full_population_weights=all_outputs_of_init[0];
    double[] full_population_biases=all_outputs_of_init[1];
    double weights_amount=all_outputs_of_init[2][0];
    double biases_amount=all_outputs_of_init[2][1];
    double[] nodes_counted_in_each_layer=all_outputs_of_init[3];

    double[] inputs={2.25};
    //activation functions are:
    /*
    1=sigmoid;
    2=binary step;
    3=tanh;
    4=softplus;
    5=gaussian;

    ACTIVATION FUNCTIONS NOT CODED YET
    ALL ELSE WORKS THOUGH
    */
    double[][][][] run_network_pagacked_outputs=run_network(full_population_weights, full_population_biases, inputs, LAYERS_BEING_USED);
    double[][][] weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer=run_network_pagacked_outputs[2];
    double[] outputs=run_network_pagacked_outputs[0][0][0];
    double[] node_firing_numbers=run_network_pagacked_outputs[1][0][0];

  }

}

//Remember to go into https://github.com/Algorithmic-TITAN/Java_version_of_gradient_decent_algorithm_with_add_ons and drag and drop the file from file explorer into the repository to update the git repository.