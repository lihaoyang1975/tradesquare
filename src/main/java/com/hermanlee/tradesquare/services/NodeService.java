package com.hermanlee.tradesquare.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hermanlee.tradesquare.domain.Node;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@Lazy
public class NodeService {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Merge and sort an array of linked lists
     * @param heads an array of linked lists
     * @return a merged and sorted linked list
     */
    public Node mergeAndSortLinkedList(Node... heads) {

        // because it's much cheaper to sort an array than to sort a linked list
        // we want to combine the linked lists into one big array, sort it,
        // and then convert it to a linked list

        // find out how big an array we need create
        int size = 0;
        for (Node head: heads) {
            size += head.getSize(); // getLinkedListSize(head);
        }

        // create the big array
        int[] numbers = new int[size];

        // now populate the big array
        int i = 0;
        for (Node head: heads) {
            Node cur = head;

            while (cur != null) {
                numbers[i] = cur.getVal();
                cur = cur.getNext();
                i++;
            }
        }

        // sort the big array
        Arrays.sort(numbers);

        // convert it to a linked list and return it
        return Node.fromArray(numbers);
    }

    // for use during testing to generate an array of pseudorandom ints of the specified length
    // then use convertArrayToLinkedList to get a linked list to test with
    public int[] generateRandomIntArray(int length) {
        Random random = new Random();
        return IntStream.generate(() -> random.nextInt()).limit(length).toArray();
    }

    // for use during testing to generate an array of sequential ints of the specified length
    // then use convertArrayToLinkedList to get a linked list to test with
    public int[] generateSequentialIntArray(int start, int end) {
        return IntStream.rangeClosed(start, end).toArray();
    }

    /**
     * persist the linked list in a file using the default serialization method
     * @param head the linked list
     * @param filename the name of the file to save to
     * @throws IOException
     */
    public void saveLinkedList(Node head, String filename) throws IOException
    {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(head);
        out.close();
    }

    /**
     * load a linked list from a file using the default deserialization method
     * @param filename the name of the file containing the serialized linked list
     * @return the linked list
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public Node loadLinkedList(String filename) throws ClassNotFoundException, IOException
    {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        Node head = (Node)in.readObject();
        in.close();
        return head;
    }

    /**
     * persist the linked list in a file using json
     * @param head the linked list
     * @param filename the name of the file to save to
     * @throws IOException
     */
    public void saveLinkedListInJson(Node head, String filename) throws IOException
    {
        BufferedWriter out = new BufferedWriter(new FileWriter(filename));
        out.write(mapper.writeValueAsString(head)); //linkedList
        out.newLine();
        out.close();
    }

    /**
     * load a linked list from a file using json
     * @param filename the name of the file containing the serialized linked list
     * @return the linked list
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public Node loadLinkedListInJson(String filename) throws ClassNotFoundException, IOException
    {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String json = in.readLine();
        in.close();

        Node head = mapper.readValue(json, Node.class);
        return head;
    }

}
