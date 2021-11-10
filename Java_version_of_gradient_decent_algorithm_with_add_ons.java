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



  public static void main(String[] args) 
  {
    int[] LAYERS_BEING_USED={1,2,5};
    double[] INITIALIZING_RANGE={-5,3};
    double[][] all_outputs_of_init=init(LAYERS_BEING_USED, INITIALIZING_RANGE);
    double[] full_population_weights=all_outputs_of_init[0];
    double[] full_population_biases=all_outputs_of_init[1];
    double weights_amount=all_outputs_of_init[2][0];
    double biases_amount=all_outputs_of_init[2][1];
    double[] nodes_counted_in_each_layer=all_outputs_of_init[3];
    System.out.println(Arrays.toString(full_population_weights));
    System.out.println(Arrays.toString(full_population_biases));
    System.out.println(weights_amount);
    System.out.println(biases_amount);
    System.out.println(Arrays.toString(nodes_counted_in_each_layer));
  }

}

//Remember to go into github and drag and drop the file from file explorer into the repository to update the git repository.