/* class name MUST BE THE SAME AS THE FILE NAME */
/*Java is capital-sensitive*/
import java.lang.Math; /*imports java's math library*/
import java.util.Random; //imports random library

import javax.lang.model.util.ElementScanner14;

import java.util.Arrays; //lets you use arrays easily
import java.lang.Integer;
import java.lang.String;
import java.lang.Double;
import java.io.*;


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


  public static double[][][][] run_network(double[] full_population_weights, double[] full_population_biases, double[] INPUTS, int[] layers, String activation_functions, double[][][] empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer)
  {
    double[] running_network_weights=full_population_weights;
    double[] running_network_biases=full_population_biases;
    double[] node_firing_numbers={};
    double[][][] weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer=empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer;
    double[] NODES_PROSCESSED=INPUTS;
    int node_firing_numbers_LEN=0;
    int nodes_proscessing_LENGTH=0;
    for (int node_firing_numbers_len_finder=0; node_firing_numbers_len_finder<layers.length; node_firing_numbers_len_finder++)
    {
        node_firing_numbers_LEN+=layers[node_firing_numbers_len_finder];
        if (nodes_proscessing_LENGTH<layers[node_firing_numbers_len_finder])
        {
          nodes_proscessing_LENGTH=layers[node_firing_numbers_len_finder];
        }
    }
    node_firing_numbers=Arrays.copyOf(node_firing_numbers, node_firing_numbers_LEN);
    for (int i=0; i<INPUTS.length; i++)
    {
      node_firing_numbers[i]=INPUTS[i];
    }
    int computationoal_part_weights=0;
    int computationoal_part_biases=0;

    for (int i1=0; i1<layers.length-1;i1++)
    {
      double[] nodes_proscessing={};
      nodes_proscessing=Arrays.copyOf(nodes_proscessing, nodes_proscessing_LENGTH);
      for (int i2=0; i2<layers[i1+1]; i2++)
      {
        double node_in_next_layer_VALUE=0;
        for (int i3=0; i3<layers[i1]; i3++)
        {
          double edge_value=running_network_weights[computationoal_part_weights]*NODES_PROSCESSED[i3];
          weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer[i1][i2][i3]=running_network_weights[computationoal_part_weights];
          computationoal_part_weights++;
          node_in_next_layer_VALUE+=edge_value;
        }
        nodes_proscessing[i2]=node_in_next_layer_VALUE+running_network_biases[computationoal_part_biases];
        node_firing_numbers[computationoal_part_biases+layers[0]]=node_in_next_layer_VALUE+running_network_biases[computationoal_part_biases];
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



  public static double test(double[][][] test_data, int[] layers, String activation_functions, double[] full_population_weights, double[] full_population_biases, double[][][] empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer)
  {
    double error=0;
    for (int i=0; i<test_data.length; i++)
    {
      for (int i1=0; i1<layers[layers.length-1]; i1++)
      {
        error+=Math.pow(run_network(full_population_weights, full_population_biases, test_data[i][0], layers, activation_functions, empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer)[0][0][0][i1]-test_data[i][1][i1], 2);
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



  public static double[][] find_which_way_to_nudge_values(int[] layers, int DATA_INSTANCE, String activation_functions, double[] full_population_weights, double[] full_population_biases, double[] empty_derivative_list_weights, double[] empty_derivative_lists_biases, double[][][] training_data, double[] last_layer_to_cost_effects_empty, double[] weight_surrounding_layer_numbers_empty, double[] nodes_counted_in_each_layer, double[][][] empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer)
  {
    activation_functions=activation_functions.replace("binary_step","");
    activation_functions=activation_functions.replace("binarystep", "");
    double[][][][] run_network_pagacked_outputs=run_network(full_population_weights, full_population_biases, training_data[DATA_INSTANCE][0], layers, activation_functions, empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer);
    double[][][] weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer=run_network_pagacked_outputs[2];
    double[] outputs=run_network_pagacked_outputs[0][0][0];
    double[] node_firing_numbers=run_network_pagacked_outputs[1][0][0];

    double[] last_layer_to_cost_effects=last_layer_to_cost_effects_empty;
    for(int i=0; i<training_data[0][1].length; i++)
    {
      double current_node_derivative_appending_to_originoal_last_layer_to_cost_effects=2*(outputs[i]-training_data[DATA_INSTANCE][1][i]);
      if (activation_functions.contains("sigmoid"))
      {
        current_node_derivative_appending_to_originoal_last_layer_to_cost_effects*=(1/(1+Math.pow(2.71828, -outputs[i])))*(1-(1/(1+Math.pow(2.71828, -outputs[i]))));
      }
      if ((activation_functions.contains("binary_step")) || activation_functions.contains("binarystep"))
      {
        current_node_derivative_appending_to_originoal_last_layer_to_cost_effects*=2*((outputs[i]-training_data[DATA_INSTANCE][1][i])*100);
      }
      if (activation_functions.contains("tanh") || activation_functions.contains("hyperbolic_tangent") || activation_functions.contains("hyperbolictangent"))
      {
        current_node_derivative_appending_to_originoal_last_layer_to_cost_effects*=1-Math.pow(((Math.pow(2.71828, outputs[i]))-(Math.pow(2.71828, -1*outputs[i])))/((Math.pow(2.71828, outputs[i]))+(Math.pow(2.71828, -1*outputs[i]))), 2);
      }
      if (activation_functions.contains("softplus"))
      { 
        current_node_derivative_appending_to_originoal_last_layer_to_cost_effects*=1/(1+(Math.pow(2.71828, -1*outputs[i])));
      }
      if (activation_functions.contains("gaussian") || activation_functions.contains("gaussian_function") || activation_functions.contains("gaussianfunction"))
      {
        current_node_derivative_appending_to_originoal_last_layer_to_cost_effects*=-2*outputs[i]*Math.pow((Math.pow(2.71828, -outputs[i])), 2);
      }
      last_layer_to_cost_effects[i]=current_node_derivative_appending_to_originoal_last_layer_to_cost_effects;
    }
    int last_layer_to_cost_effects_LEN=layers[layers.length-1];
    int biases_went_back_counter=0;
    int weights_went_back_counter=0;
    double influence_amount=0; //making it avalible for the whole funcion... widening scope of var
    double summed_influence_on_next_layer=0; //making it avalible for the whole funcion... widening scope of var
    double effect_of_bias=0; //making it avalible for the whole funcion... widening scope of var
    double[] derivative_list_weights=empty_derivative_list_weights;
    double[] derivative_list_biases=empty_derivative_lists_biases;
    for(int i1=0; i1<layers.length-1; i1++)
    {
      for (int i2=0; i2<layers[layers.length-i1-1]; i2++)
      {
        if (i1==0)
        {
          influence_amount=-1;
        }
        else
        {
          influence_amount=layers[layers.length-i1];
        }
        summed_influence_on_next_layer=0;
        if (!(influence_amount==-1))
        {
          for(int i4=0; i4<influence_amount; i4++)
          {
            double weight_carrying_effect_value=weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer[layers.length-i1-1][i4][i2];
            double effects_in_the_last_layer=weight_carrying_effect_value*last_layer_to_cost_effects[i4];
            summed_influence_on_next_layer+=effects_in_the_last_layer;
          }
          last_layer_to_cost_effects[i2]=summed_influence_on_next_layer;
        }
        else
        {
          effect_of_bias=last_layer_to_cost_effects[i2];
          last_layer_to_cost_effects[i1]=effect_of_bias;
        }
      }
            
      if (!(influence_amount==-1))
      {
      last_layer_to_cost_effects_LEN=layers[layers.length-i1-1];
      }
      for(int i3=0; i3<(last_layer_to_cost_effects.length-last_layer_to_cost_effects_LEN); i3++)
      {
        last_layer_to_cost_effects[i3+last_layer_to_cost_effects_LEN]=(double)0;
      }

      for (int i6=0; i6<layers[layers.length-i1-1]; i6++)
      {
        for(int i7=0; i7<layers[layers.length-i1-2]; i7++)
        {
          derivative_list_weights[derivative_list_weights.length-weights_went_back_counter-1]=(last_layer_to_cost_effects[last_layer_to_cost_effects_LEN-i6-1])*node_firing_numbers[(int)((layers[layers.length-i1-2]-i7-1)+nodes_counted_in_each_layer[layers.length-i1-2])];
          weights_went_back_counter++;
        }
      }
      for (int i5=0; i5<layers[layers.length-1-i1]; i5++)
      {
        derivative_list_biases[derivative_list_biases.length-biases_went_back_counter-1]=last_layer_to_cost_effects[i5];
        biases_went_back_counter++;
      }


    }
    double[][] packaged_outputs={{},{}};
    packaged_outputs[1]=derivative_list_biases;
    packaged_outputs[0]=derivative_list_weights;

    return packaged_outputs;
  }




  public static double[][] take_gradient_decent_step(int[] layers, String activation_functions, double[][][] training_data, double weights_amount, double biases_amount, double[] full_population_weights, double[] full_population_biases, double learning_rate, double[] empty_derivative_list_weights, double[] empty_derivative_lists_biases, double[] last_layer_to_cost_effects_empty, double[] weight_surrounding_layer_numbers_empty, double[] nodes_counted_in_each_layer,double[][][] empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer)
  {
    double[][] packaged_outputs={full_population_weights,full_population_biases};

    for(int i=0; i<training_data.length; i++)
    {
      double[][] weights_and_biases_packaged_derivatives=find_which_way_to_nudge_values(layers, i, activation_functions, full_population_weights, full_population_biases, empty_derivative_list_weights, empty_derivative_lists_biases, training_data, last_layer_to_cost_effects_empty, weight_surrounding_layer_numbers_empty, nodes_counted_in_each_layer, empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer);
      double[] derivatives_in_network_weights=weights_and_biases_packaged_derivatives[0];
      double[] derivatives_in_network_biases=weights_and_biases_packaged_derivatives[1];
      for (int i1=0; i1<weights_amount; i1++)
      {
        packaged_outputs[0][i1]+=derivatives_in_network_weights[i1]*learning_rate*-1/training_data.length;
      }
      for(int i2=0; i2<biases_amount; i2++)
      {
        packaged_outputs[1][i2]+=(derivatives_in_network_biases[i2]*learning_rate*-1)/training_data.length;
      }
    }



    return packaged_outputs;
  }



  public static double[][] empty_deriavative_lists_for_speed(double weights_amount, double biases_amount)
  {
    double[] empty_weights_derivative_list={};
    double[] empty_biases_derivative_list={};
    for (int i=0; i<weights_amount; i++)
    {
      empty_weights_derivative_list=Arrays.copyOf(empty_weights_derivative_list, empty_weights_derivative_list.length+1);
      empty_weights_derivative_list[empty_weights_derivative_list.length-1]=(double) 0;
    }
    for (int i1=0; i1<biases_amount; i1++)
    {
      empty_biases_derivative_list=Arrays.copyOf(empty_biases_derivative_list, empty_biases_derivative_list.length+1);
      empty_biases_derivative_list[empty_biases_derivative_list.length-1]=(double) 0;
    }
    double[][] function_outupts={empty_weights_derivative_list, empty_biases_derivative_list};
    return function_outupts;
  }

  public static double[][] last_layer_to_cost_effects_and_weight_surrounding_layer_numbers_empty_lists_for_efficiency(int[] layers_for_efficiency)
  {
    double[] last_layer_to_cost_effects_EMPTY={};
    double[] weight_surrounding_layer_numbers_EMPTY={0,0};
    int max_layer_sums_ID_of_first_layer=-1;
    int max_sum=0;
    for(int i1=0; i1<layers_for_efficiency.length-1; i1++)
    {
      if((layers_for_efficiency[i1]+layers_for_efficiency[i1+1])>max_sum)
      {
        max_layer_sums_ID_of_first_layer=i1;
        max_sum=(layers_for_efficiency[i1]+layers_for_efficiency[i1+1]);
      }
    }
    for(int i=0; i<max_sum; i++)
    {
      last_layer_to_cost_effects_EMPTY=Arrays.copyOf(last_layer_to_cost_effects_EMPTY, last_layer_to_cost_effects_EMPTY.length+1);
      last_layer_to_cost_effects_EMPTY[last_layer_to_cost_effects_EMPTY.length-1]=(double)0;
    }
    int[] layers=layers_for_efficiency;
    double[][] function_outputs={last_layer_to_cost_effects_EMPTY, weight_surrounding_layer_numbers_EMPTY};
    return function_outputs;
  }

  public static String get_text_data()
  {
      BufferedReader file_contents;
      String file_contents_string="";
      try
      {
          file_contents=new BufferedReader(new FileReader(("C:\\Users\\andre\\OneDrive\\Desktop\\text_to_array_file_converter_file\\text_to_read_from.txt"))); //DO NOT CHANGE THIS PATH!!!!!
          file_contents_string=file_contents.readLine();
      }
      catch (Exception ex)
      {

      }
      return file_contents_string;
  }

  public static double[][][] convert_data(String text_file_text_in_string, int data_instance_amount, int network_inputs_amount,  int network_outputs_amount, boolean print_data)
  {
      //getting ready for main conversion loop
      text_file_text_in_string=text_file_text_in_string.replace(" ", "");
      double[][][] end_array={};
      double[][] sample_data_instance_empty={{},{}};
      end_array=Arrays.copyOf(end_array, data_instance_amount);
      for (int end_array_making_for_loop=0; end_array_making_for_loop<data_instance_amount; end_array_making_for_loop++)
      {
          end_array[end_array_making_for_loop]=sample_data_instance_empty;
      }
      int character_counter_passed_all=1;


      //main conversion loop
      for(int i=0; i<data_instance_amount; i++)
      {
          boolean end_of_instance_reached=false; //FOR BOTH INPUTS AND OUTPUTS
          int character_counter_passed_data_intsance=0; //FOR BOTH INPUTS AND OUTPUTS
          int array_ends_found_so_far=0; //FOR BOTH INPUTS AND OUTPUTS

          String string_version_of_what_to_convert_INPUTS=""; //FOR INPUTS
          int inputs_finished_so_far=0; //FOR ONLY INPUTS
          boolean currently_REALLY_inside_inputs=false; //FOR ONLY INPUTS
          boolean currently_counting_inside_inputs=false; //FOR ONLY INPUTS
          double[] current_inputs_finding={}; //FOR ONLY INPUTS
          current_inputs_finding=Arrays.copyOf(current_inputs_finding, network_inputs_amount); //FOR ONLY INPUTS

          String string_version_of_what_to_convert_OUTPUTS=""; //FOR OUTPUTS
          boolean currently_inside_outputs=false; //FOR ONLY OUTPUTS
          boolean currently_REALLY_inside_outputs=false; //FOR ONLY OUTPUTS
          int outputs_finished_so_far=0; //FOR ONLY OUTPUTS
          double[] current_outputs_finding={};
          current_outputs_finding=Arrays.copyOf(current_outputs_finding, network_outputs_amount);

          while(end_of_instance_reached==false)
          {
              if((text_file_text_in_string.charAt(character_counter_passed_all+character_counter_passed_data_intsance)==',') && (currently_counting_inside_inputs==true)) //FOR INPUTS. CHANGE TO MAKE IT DIFFERENT FROM OUTPTUS!!!
              {
                  currently_counting_inside_inputs=false;
                  double double_converted_inputs=Double.parseDouble(string_version_of_what_to_convert_INPUTS);
                  current_inputs_finding[inputs_finished_so_far]=double_converted_inputs;
                  inputs_finished_so_far+=1;
                  string_version_of_what_to_convert_INPUTS="";
              }
              if ((text_file_text_in_string.charAt(character_counter_passed_all+character_counter_passed_data_intsance)=='}') && (currently_REALLY_inside_inputs=true)) //FOR INPUTS
              {
                  double double_converted_inputs;
                  currently_counting_inside_inputs=false;
                  currently_REALLY_inside_inputs=false;
                  try
                  {
                      double_converted_inputs=Double.parseDouble(string_version_of_what_to_convert_INPUTS);
                      current_inputs_finding[inputs_finished_so_far]=double_converted_inputs;
                  }
                  catch (Exception ex)
                  {
                      double_converted_inputs=0.0;
                  }
              }

              if((text_file_text_in_string.charAt(character_counter_passed_all+character_counter_passed_data_intsance)==',') && (currently_inside_outputs==true)) //FOR OUTPUTS.
              {
                  currently_inside_outputs=false;
                  double double_converted_outputs=Double.parseDouble(string_version_of_what_to_convert_OUTPUTS);
                  current_outputs_finding[outputs_finished_so_far]=double_converted_outputs;
                  outputs_finished_so_far+=1;
                  string_version_of_what_to_convert_OUTPUTS="";
              }
              if ((text_file_text_in_string.charAt(character_counter_passed_all+character_counter_passed_data_intsance)=='}') && (currently_REALLY_inside_outputs=true)) //FOR OUTPUTS
              {
                  double double_converted_outputs;
                  currently_inside_outputs=false;
                  currently_REALLY_inside_outputs=false;
                  try
                  {
                      double_converted_outputs=Double.parseDouble(string_version_of_what_to_convert_OUTPUTS);
                      current_outputs_finding[outputs_finished_so_far]=double_converted_outputs;
                  }
                  catch (Exception ex)
                  {
                      double_converted_outputs=0.0;
                  }
              }

              
              if (text_file_text_in_string.charAt(character_counter_passed_all+character_counter_passed_data_intsance)=='}') //FOR BOTH INPUTS AND OUTPUTS
              {
                  array_ends_found_so_far+=1;
                  currently_counting_inside_inputs=false;
                  currently_REALLY_inside_inputs=false;
                  currently_inside_outputs=false;
                  currently_REALLY_inside_outputs=false;
                  string_version_of_what_to_convert_INPUTS="";
                  string_version_of_what_to_convert_OUTPUTS="";
              }
              if(array_ends_found_so_far==3) //FOR BOTH INPUTS AND OUTPUTS
              {
                  end_of_instance_reached=true;
              }

              if (currently_counting_inside_inputs==true) //FOR ONLY INPUTS
              {
                  string_version_of_what_to_convert_INPUTS=string_version_of_what_to_convert_INPUTS.concat(String.valueOf(text_file_text_in_string.charAt(character_counter_passed_all+character_counter_passed_data_intsance)));
              }
              if (currently_inside_outputs==true) //FOR ONLY OUTUPTS
              {
                  string_version_of_what_to_convert_OUTPUTS=string_version_of_what_to_convert_OUTPUTS.concat(String.valueOf(text_file_text_in_string.charAt(character_counter_passed_all+character_counter_passed_data_intsance)));
              }


              if((text_file_text_in_string.charAt(character_counter_passed_all+character_counter_passed_data_intsance)=='{') && (text_file_text_in_string.charAt(character_counter_passed_all+character_counter_passed_data_intsance-1)=='{') && !(character_counter_passed_all+character_counter_passed_data_intsance-2==-1)) //for inputs
              {
                  currently_counting_inside_inputs=true;
                  currently_REALLY_inside_inputs=true;
                  string_version_of_what_to_convert_INPUTS="";
              }
              if ((text_file_text_in_string.charAt(character_counter_passed_all+character_counter_passed_data_intsance)=='{') && (text_file_text_in_string.charAt(character_counter_passed_all+character_counter_passed_data_intsance-1)==',') && !(text_file_text_in_string.charAt(character_counter_passed_all+character_counter_passed_data_intsance-3)=='}')) //for outputs
              {   
                  currently_inside_outputs=true;
                  currently_REALLY_inside_outputs=true;
                  string_version_of_what_to_convert_OUTPUTS="";
              }

              if ((text_file_text_in_string.charAt(character_counter_passed_all+character_counter_passed_data_intsance)==',') && currently_REALLY_inside_inputs) //FOR INPUTS
              {
                  currently_counting_inside_inputs=true;
              }
              if ((text_file_text_in_string.charAt(character_counter_passed_all+character_counter_passed_data_intsance)==',') && currently_REALLY_inside_outputs) //FOR OUTUPTS
              {
                  currently_inside_outputs=true;
              }
              character_counter_passed_data_intsance+=1;
          }
          end_array[i]=new double[][] {current_inputs_finding, current_outputs_finding};
          character_counter_passed_all+=character_counter_passed_data_intsance;
      }

      //printing stuff and returning
      if (print_data==true)
      {
      System.out.print("Text file data: ");
      System.out.println(text_file_text_in_string);
      }
      return end_array;
  }

  public static double[][][] initialize_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer_for_speed(int[] layers)
  {
    double[][][] output_array={};
    output_array=Arrays.copyOf(output_array, layers.length-1);
    for (int i=0; i<output_array.length; i++)
    {
      output_array[i]=Arrays.copyOf(new double[][] {}, layers[i+1]);
      for (int i1=0; i1<output_array[i].length; i1++)
      {
        output_array[i][i1]=new double[] {};
        output_array[i][i1]=Arrays.copyOf(output_array[i][i1], layers[i]);
      }
    }
    
    return output_array;
  }

  public static double[][] envy_emotion(double test_output, double[][][] test_data, int[] layers, String activation_functions, double[] full_population_weights, double[] full_population_biases, double[][][] empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer, double envy_resistance_level, double data_group_amount_envy, double weights_amount, double biases_amount, double learning_rate, double[] empty_derivative_list_weights, double[] empty_derivative_lists_biases, double[] last_layer_to_cost_effects_empty, double[] weight_surrounding_layer_numbers_empty, double[] nodes_counted_in_each_layer)
  {
    double[] new_weights={};
    double[] new_biases={};

    double[][][][] training_data_training_groups={};
    double data_group_size_avg=Math.round(test_data.length/data_group_amount_envy)-1;
    int current_data_instance_to_append_index=0;
    double overall_test_error=test_output;
    int current_data_instance_to_append_index_GROUP=0;
    if (overall_test_error>=envy_resistance_level)
    {
      int current_group_appending_index=0;
      training_data_training_groups=Arrays.copyOf(new double[][][][] {}, (int)data_group_amount_envy+1);
      for(int i=0; i<data_group_amount_envy; i++)
      {
        training_data_training_groups[current_group_appending_index]=Arrays.copyOf(new double[][][] {}, (int)data_group_size_avg);
        current_data_instance_to_append_index_GROUP=0;
        for (int i1=0; i1<data_group_size_avg; i1++)
        {
          training_data_training_groups[current_group_appending_index][current_data_instance_to_append_index_GROUP]=test_data[current_data_instance_to_append_index];
          current_data_instance_to_append_index++;
          current_data_instance_to_append_index_GROUP++;
        }
        current_group_appending_index++;
      }
      training_data_training_groups[current_group_appending_index]=Arrays.copyOf(new double[][][] {}, (int)test_data.length-current_data_instance_to_append_index);
      current_data_instance_to_append_index_GROUP=0;
      for (int i2=0; i2<test_data.length-current_data_instance_to_append_index; i2++)
      {
        training_data_training_groups[current_group_appending_index][current_data_instance_to_append_index_GROUP]=test_data[current_data_instance_to_append_index];
        current_data_instance_to_append_index++;
      }

      for (int i3=0; i3<training_data_training_groups.length-1; i3++)
      {
        double[][] new_weights_and_biases=take_gradient_decent_step(layers, activation_functions, training_data_training_groups[i3], weights_amount, biases_amount, full_population_weights, full_population_biases, learning_rate, empty_derivative_list_weights, empty_derivative_lists_biases, last_layer_to_cost_effects_empty, weight_surrounding_layer_numbers_empty, nodes_counted_in_each_layer, empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer);
        new_weights=new_weights_and_biases[0];
        new_biases=new_weights_and_biases[1];
      }
      
    }
    else
    {
      new_weights=full_population_weights;
      new_biases=full_population_biases;
    }
    return new double[][] {new_weights, new_biases};
  }



  public static double[][][] awe_emotion(double test_output, double awe_expectation_level, double[][][] test_data, int[] layers, String activation_functions, double[] full_population_weights, double[] full_population_biases, double[][][] empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer, double[][][] recorded_networks_so_far)
  {
    double[][][] recorded_networks_so_far_mutatable=recorded_networks_so_far;
    if (awe_expectation_level>test_output)
    {
      recorded_networks_so_far_mutatable=Arrays.copyOf(recorded_networks_so_far_mutatable, recorded_networks_so_far_mutatable.length+1);
      recorded_networks_so_far_mutatable[recorded_networks_so_far_mutatable.length-1]=new double[][] {full_population_weights, full_population_biases, {test_output}};
    }

    return recorded_networks_so_far_mutatable;
  }

  public static double[][][][] boredom_emotion(double test_output, double awe_expectation_level, double[][][] test_data, int[] layers, String activation_functions, double[] full_population_weights, double[] full_population_biases, double[][][] empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer, double[][][] recorded_networks_so_far_boredom, double boredom_acceptance_level, double[] initializing_range, double epochs_done)
  {
    double[] full_population_weights_new=null;
    double[] full_population_biases_new=null;
    double[][][] recorded_networks_so_far_mutatable_boredom=recorded_networks_so_far_boredom;
    double just_reinitialized=0;

    try
    {
      if (((recorded_networks_so_far_boredom[0][2][0]-test_output)/(epochs_done))<boredom_acceptance_level)
      {
        double[][] outputs_of_init_boredom=init(layers, initializing_range);
        full_population_weights_new=outputs_of_init_boredom[0];
        full_population_biases_new=outputs_of_init_boredom[1];
        recorded_networks_so_far_mutatable_boredom=new double[][][] {{full_population_weights_new, full_population_biases_new, {test(test_data, layers, activation_functions, full_population_weights_new, full_population_biases_new, empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer)}}};
        just_reinitialized=1;
      }
      else
      {
        full_population_weights_new=full_population_weights;
        full_population_biases_new=full_population_biases;
      }
    }
    catch (Exception ex)
    {
      full_population_weights_new=full_population_weights;
      full_population_biases_new=full_population_biases;
    }

    return new double[][][][] {recorded_networks_so_far_mutatable_boredom, {{full_population_weights_new, full_population_biases_new}}, {{{just_reinitialized}}}};
  }



  public static double[][] confusion_emotion(double[] full_population_weights, double[] full_population_biases, double confusion_amount, double confusion_commonness_percent)
  {
    double[] full_population_weights_new=full_population_weights;
    double[] full_population_biases_new=full_population_biases;
    for (int i=0; i<full_population_weights.length; i++)
    {
      if ((Math.random()*100)<confusion_commonness_percent)
      {
        full_population_weights_new[i]+=((Math.random()*2)-1)*confusion_amount;
      }
    }
    for (int i1=0; i1<full_population_biases.length; i1++)
    {
      if ((Math.random()*100)<confusion_commonness_percent)
      {
        full_population_biases_new[i1]+=((Math.random()*2)-1)*confusion_amount;
      }
    }
    
    return new double[][] {full_population_weights_new, full_population_biases_new};
  }

  
  public static double[][][] triumph_emotion(double triumph_resistance, double[][][] recorded_networks_so_far)
  {
    double[][][] triumph_emotion_outputs;
    int best_network_recorded_index=0;
    if (recorded_networks_so_far.length>triumph_resistance)
    {
      best_network_recorded_index=0;
      double best_network_recorded_error=recorded_networks_so_far[0][2][0];
      for (int i=0; i<recorded_networks_so_far.length; i++)
      {
        if (recorded_networks_so_far[i][2][0]<best_network_recorded_error)
        {
          best_network_recorded_index=i;
          best_network_recorded_error=recorded_networks_so_far[i][2][0];
        }
      }
      recorded_networks_so_far=new double[][][] {recorded_networks_so_far[best_network_recorded_index]};
    }
    try
    {
      triumph_emotion_outputs=recorded_networks_so_far;
    }
    catch (Exception ex)
    {
      triumph_emotion_outputs=new double[][][] {};
    }
    return triumph_emotion_outputs;
  }



  public static double[][][][][] control_emotions(double test_output,double[][][] test_data, int[] layers, String activation_functions, double[] full_population_weights, double[] full_population_biases, double[][][] empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer, double envy_resistance_level, double data_group_amount_envy, double weights_amount, double biases_amount, double learning_rate, double[] empty_derivative_list_weights, double[] empty_derivative_lists_biases, double[] last_layer_to_cost_effects_empty, double[] weight_surrounding_layer_numbers_empty, double[] nodes_counted_in_each_layer, double awe_expectation_level, double[][][] recorded_networks_so_far, double[][][] recorded_networks_so_far_boredom, double boredom_acceptance_level, double[] initializing_range, double epochs_done, double confusion_amount, double confusion_commonness_percent, double triumph_resistance)
  {
    double[][] envy_outputs=envy_emotion(test_output, test_data, layers, activation_functions, full_population_weights, full_population_biases, empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer, envy_resistance_level, data_group_amount_envy, weights_amount, biases_amount, learning_rate, empty_derivative_list_weights, empty_derivative_lists_biases, last_layer_to_cost_effects_empty, weight_surrounding_layer_numbers_empty, nodes_counted_in_each_layer); //envy makes sure that (if it is considered right by the person running the algorihm average error wise), it will split up the data so it trains quickly and well, while envy is running.
    full_population_weights=envy_outputs[0];
    full_population_biases=envy_outputs[1];
    double[][][] awe_emotion_outupts=awe_emotion(test_output, awe_expectation_level, test_data, layers, activation_functions, full_population_weights, full_population_biases, empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer, recorded_networks_so_far); //awe makes sure that all networks that are considered good by the person running the algorithm (average error wise) are taken note of.
    double[][][][] boredom_emotion_outputs=boredom_emotion(test_output, awe_expectation_level, test_data, layers, activation_functions, full_population_weights, full_population_biases, empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer, recorded_networks_so_far_boredom, boredom_acceptance_level, initializing_range, epochs_done); //boredom makes sure that multiple networks can be trained, and it keeps track of the ones that it has trashed.
    full_population_weights=boredom_emotion_outputs[1][0][0];
    full_population_biases=boredom_emotion_outputs[1][0][1];
    double just_reinitialized=boredom_emotion_outputs[2][0][0][0];
    double[][] confusion_outputs=confusion_emotion(full_population_weights, full_population_biases, confusion_amount, confusion_commonness_percent); //confusion lets it make slight random changes to the network, which can be good or bad.
    full_population_weights=confusion_outputs[0];
    full_population_biases=confusion_outputs[1];
    double[][][] triumph_emotion_outupts=triumph_emotion(triumph_resistance, awe_emotion_outupts); //TRIUMPH CLEARS AWE, FOR THE MOST PART.
    awe_emotion_outupts=triumph_emotion_outupts; //THIS IS BECAUSE TRIUMPH MODIFIES AWE. DO NOT REMOVE, OR TRIUMPH WILL BE USELESS. TRIUMPH IS USEFUL.
    double[][][][][] outputs_of_control_emotions={{awe_emotion_outupts}, boredom_emotion_outputs, {{{{just_reinitialized}}}}, {{{full_population_weights, full_population_biases}}}};
    return outputs_of_control_emotions;
  }



  public static void main(String[] args)
  {
    //things you can specify
    int[] LAYERS_BEING_USED_FOR_EFFICIENCY={17,10,10,1};
    int[] LAYERS_BEING_USED={17,10,10,1};
    double[] INITIALIZING_RANGE={-0.3,0.3};
    int data_instance_amount=26482;
    //int data_instance_amount=1;
    System.out.println("Retreiving data...");
    String STRING_VERSION_OF_DATA=get_text_data();
    double[][][] data=convert_data(STRING_VERSION_OF_DATA, data_instance_amount, LAYERS_BEING_USED[0], LAYERS_BEING_USED[LAYERS_BEING_USED.length-1], false);
    String activation_functions="";
    double[] ratios_of_data={0,1};
    double envy_resistence_level=50;
    double data_group_amount_envy=50;
    double awe_expectation_level=55;
    double boredom_acceptance_level=7.5;
    double confusion_amount=0.001;
    double confusion_commonness_percent=10;
    double triumph_resistance=5;
    final double learning_rate=0.00001; //make sure this is high enough so that boredom doesn't trigger.
    final int epoch_amount=10000; //1000000000 is the max factor of 10 becaue otherwise it would be a long or another datatype, but this is for all practical uses infinity.

    System.out.println("Getting ready...");
    //getting ready for the main computation
    double[][] all_outputs_of_init=init(LAYERS_BEING_USED, INITIALIZING_RANGE);
    double[][][] initialized_empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer_for_speed=initialize_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer_for_speed(LAYERS_BEING_USED);
    double[][][][] train_test_data_split_outputs=train_test_data_split(data, ratios_of_data);
    double[][][] training_data=train_test_data_split_outputs[0];
    double[][][] testing_data=train_test_data_split_outputs[1];
    double[] full_population_weights=all_outputs_of_init[0];
    double[] full_population_biases=all_outputs_of_init[1];
    double weights_amount=all_outputs_of_init[2][0];
    double biases_amount=all_outputs_of_init[2][1];
    double[] nodes_counted_in_each_layer=all_outputs_of_init[3];
    double[][] empty_derivative_lists=empty_deriavative_lists_for_speed(weights_amount, biases_amount);
    double[] empty_derivative_list_weights=empty_derivative_lists[0];
    double[] empty_derivative_lists_biases=empty_derivative_lists[1];
    double[][] packaged_last_layer_to_cost_effects_and_weight_surrounding_layer_numbers_empty_lists_for_efficiency_outputs=last_layer_to_cost_effects_and_weight_surrounding_layer_numbers_empty_lists_for_efficiency(LAYERS_BEING_USED_FOR_EFFICIENCY);
    double[] last_layer_to_cost_effects_EMPTY=packaged_last_layer_to_cost_effects_and_weight_surrounding_layer_numbers_empty_lists_for_efficiency_outputs[0];
    double[] weight_surrounding_layer_numbers_EMPTY=packaged_last_layer_to_cost_effects_and_weight_surrounding_layer_numbers_empty_lists_for_efficiency_outputs[1];
    double[][][][][] control_emotions_outputs={};
    double[][][] recorded_networks_so_far={};
    double[][][] recorded_networks_so_far_boredom={{full_population_weights, full_population_biases, {test(training_data, LAYERS_BEING_USED, activation_functions, full_population_weights, full_population_biases, initialized_empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer_for_speed)}}};
    double epochs_done_boredom=0;
    int local_minimums_considered=0;


    //If you get an NaN as an output, just make the initializing range smaller, or the learning rate
    System.out.println("Starting! ");
    //Main loop that actually trains the network
    System.out.print("the overall error so far is ");
    System.out.println(test(training_data, LAYERS_BEING_USED, activation_functions, full_population_weights, full_population_biases, initialized_empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer_for_speed));
    for (double i=0; i<epoch_amount; i++)
    {
      long start_time_milliseconds=System.currentTimeMillis();
      double[][] new_weights_and_biases=take_gradient_decent_step(LAYERS_BEING_USED, activation_functions, training_data, weights_amount, biases_amount, full_population_weights, full_population_biases, learning_rate, empty_derivative_list_weights, empty_derivative_lists_biases, last_layer_to_cost_effects_EMPTY, weight_surrounding_layer_numbers_EMPTY, nodes_counted_in_each_layer, initialized_empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer_for_speed);
      full_population_weights=new_weights_and_biases[0];
      full_population_biases=new_weights_and_biases[1];
      if (i/100==Math.round(i/100))
      {    
        try
        {  
        int best_network_recorded_index=0;
        double best_network_recorded_error=recorded_networks_so_far[0][2][0];
        for (int best_network_finding_main=0; best_network_finding_main<recorded_networks_so_far.length; best_network_finding_main++)
        {
          if (recorded_networks_so_far[best_network_finding_main][2][0]<best_network_recorded_error)
          {
            best_network_recorded_index=best_network_finding_main;
            best_network_recorded_error=recorded_networks_so_far[best_network_finding_main][2][0];
          }
        }

        System.out.print("Best weights so far: ");
        System.out.println(Arrays.toString(recorded_networks_so_far[best_network_recorded_index][0]));
        System.out.print("Best biases so far: ");
        System.out.println(Arrays.toString(recorded_networks_so_far[best_network_recorded_index][1]));
        }
        catch (Exception ex)
        {

        }
      }


      System.out.print(i+" epochs have passed    ");
      System.out.print("(");
      System.out.print(System.currentTimeMillis()-start_time_milliseconds);
      System.out.print("ms)  ");
      System.out.print(local_minimums_considered);
      System.out.print(" local minimums have been considered     ");
      double rounded_epoch_amount=Math.round(i/10);
      if ((i/10)==rounded_epoch_amount)
      {
        epochs_done_boredom++;
        double test_output=test(training_data, LAYERS_BEING_USED, activation_functions, full_population_weights, full_population_biases, initialized_empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer_for_speed);
        control_emotions_outputs=control_emotions(test_output, training_data, LAYERS_BEING_USED, activation_functions, full_population_weights, full_population_biases, initialized_empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer_for_speed, envy_resistence_level, data_group_amount_envy, weights_amount, biases_amount, learning_rate, empty_derivative_list_weights, empty_derivative_lists_biases, last_layer_to_cost_effects_EMPTY, weight_surrounding_layer_numbers_EMPTY, nodes_counted_in_each_layer, awe_expectation_level, recorded_networks_so_far, recorded_networks_so_far_boredom, boredom_acceptance_level, INITIALIZING_RANGE, epochs_done_boredom, confusion_amount, confusion_commonness_percent, triumph_resistance);
        try
        {
          recorded_networks_so_far=control_emotions_outputs[0][0];
        }
        catch(Exception ex)
        {

        }

        try
        {
          recorded_networks_so_far_boredom=control_emotions_outputs[1][0];
        }
        catch(Exception ex)
        {

        }
        if (control_emotions_outputs[2][0][0][0][0]==(double)1)
        {
          epochs_done_boredom=0;
          local_minimums_considered++;
        }
        full_population_weights=control_emotions_outputs[3][0][0][0];
        full_population_biases=control_emotions_outputs[3][0][0][1];
        System.out.print("the overall error so far is " + test(training_data, LAYERS_BEING_USED, activation_functions, full_population_weights, full_population_biases, initialized_empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer_for_speed)+ "     ");
        try
        {  
          int best_network_recorded_index=0;
          double best_network_recorded_error=recorded_networks_so_far[0][2][0];
          for (int best_network_finding_main=0; best_network_finding_main<recorded_networks_so_far.length; best_network_finding_main++)
          {
            if (recorded_networks_so_far[best_network_finding_main][2][0]<best_network_recorded_error)
            {
              best_network_recorded_index=best_network_finding_main;
              best_network_recorded_error=recorded_networks_so_far[best_network_finding_main][2][0];
            }
          }
          System.out.println("the error of the best network is "+ best_network_recorded_error);
        }
        catch (Exception ex)
        {
          System.out.println("the error of the best network is "+ test(training_data, LAYERS_BEING_USED, activation_functions, full_population_weights, full_population_biases, initialized_empty_weights_sorted_in_layers_then_second_connection_in_layer_then_first_connection_in_layer_for_speed));
        }
      }
      else
      {
        System.out.println();
      }
    }
    System.out.println("Stuff done");

    //EVERYTHING IN THIS ALGORITHM IS DONE. OTHER THAN ADD-ONS AND THE GENETIC ALGORITHM, IT IS FULLY CAUGHT UP TO PYTHON.
    //MAKE IT PRINT THE BEST NETWORK, NOT THE CURRENT ONE.

  } 

}


//Remember to go into https://github.com/Algorithmic-TITAN/Java_version_of_gradient_decent_algorithm_with_add_ons and drag and drop the file from file explorer (in folder src) into the repository to update the git repository.