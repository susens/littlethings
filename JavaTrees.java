import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

//import com.sun.xml.internal.fastinfoset.sax.Properties;

import sun.reflect.generics.tree.Tree;

public class JavaTrees {
		
	private static DefaultMutableTreeNode first_node= null;
	private static DefaultMutableTreeNode second_node= null;
	private static DefaultMutableTreeNode first_node3= null;

	public static void main(String[] args) {
    
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		String daten ="dn: dc=example,dc=com\n"
				+ "dn: ou=devel,dc=example,dc=com\n"
				+ "dn: ou=doc,dc=example,dc=com\n"
				+ "dn: ou=it,dc=example,dc=com\n"
				+ "dn: cn=Tux Linux,ou=devel,dc=example,dc=com\n"
				+ "dn: cn=Tux Linux1,ou=doc, dc=example,dc=com\n"
				+ "dn: cn=Tux Linux2,ou=doc,dc=example,dc=com\n"
				+ "dn: cn=Tux Linux3,ou=doc,dc=example,dc=com\n"
				+ "dn: cn=Tux Linux4,ou=devel,dc=example,dc=com\n"
				+ "dn: cn=Tux Linux5,ou=doc, dc=example,dc=com\n"
				+ "dn: cn=Tux Linux6,ou=doc,dc=example,dc=com\n"
				+ "dn: cn=Tux Linux7,ou=doc,dc=example,dc=com\n"

				
				+ "dn: cn=test,dc=example,dc=com\n";
		
		
		String[] 	temp 	= daten.split("\n");
		String 	matcher		= temp[0];
		  		matcher		= matcher.replaceAll("dn: ", "");
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode( matcher );
		
		
		for(int i=1; i<temp.length; i++)
		{
			if(temp[i].contains(matcher) && ! temp[i].contains("cn"))
			{
				String node = temp[i].replaceAll(matcher, "");
					   node = node.replaceAll("dn: ", "");
					   node = node.replaceAll(",", "");
			
				first_node = new DefaultMutableTreeNode( node );
				root.add( first_node );	
				String[] helper = temp[i].split(",");
				pruefe_unternodes(helper, daten);
					
			}
			else
			{
					String[] ausweich=temp[i].split(",");
					if(ausweich.length==3)
					{
						first_node3 = new DefaultMutableTreeNode( ausweich[0] );
						root.add(first_node3);
					}	
			}
		}
		
		
		JTree tree = new JTree( root );
		frame.add( new JScrollPane( tree ) );
		frame.pack();
		frame.setVisible( true );
		
	}

	
	private static void pruefe_unternodes(String[] helper, String daten) {
		// TODO Auto-generated method stub
		
		String 		node 	= helper[0].replaceAll("dn: ", "");
		String[] 	temp 	= daten.split("\n");
		for(int i=0; i<temp.length; i++) {
			if(temp[i].contains(node) && ! temp[i].startsWith("dn: "+node))
			{
				String[] second_node2=temp[i].split(",");
				
				second_node= new DefaultMutableTreeNode(second_node2[0]);
				first_node.add(second_node);
				
			}
		}
	}


	private static void pruefe_unternodes3()
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		DefaultMutableTreeNode root, child;
		DefaultTreeModel chosen = null;
		
		root = new DefaultMutableTreeNode("dc=example,dc=com");
		JTree tree = new JTree(root);
		DefaultTreeModel model = null;
		
		String daten = "dn: dc=example,dc=com\n"
				+ "dn: ou=devel,dc=example,dc=com\n"	
				+ "dn: ou=doc,dc=example,dc=com\n"
				+ "dn: ou=it,dc=example,dc=com\n"
				+ "dn: cn=Tux Linux,ou=devel,dc=example,dc=com\n"
				+ "dn: cn=Tux Linux1,ou=doc,dc=example,dc=com\n"
				+ "dn: cn=Tux Linux2,ou=doc,dc=example,dc=com\n"
				+ "dn: cn=Tux Linux3,ou=doc,dc=example,dc=com\n";
		
		String[][] data = {
				{            "dc=com","dn: dc=example"},
				{ "dc=com","dc=example","dn: ou=devel"},
				{ "dc=com","dc=example","dn: ou=doc"},
				{ "dc=com","dc=example","dn: ou=it"},
				{ "dc=com","dc=example","ou=devel","dn: cn=Tux Linux"},
				{ "dc=com","dc=example","ou=doc","dn: cn=Tux Linux1"},
				{ "dc=com","dc=example","ou=doc","dn: cn=Tux Linux2"},
				{ "dc=com","dc=example","ou=doc","dn: cn=Tux Linux3"},
		}; 
		
		String[][] data2 = {
				{            		  		 "dn: dc=example","dc=com"},
				{ "dn: ou=devel"	 			,"dc=example","dc=com"},
				{ "dn: ou=doc"  	 			,"dc=example","dc=com"},
				{ "dn: ou=it"   	 			,"dc=example","dc=com"},
				{ "dn: cn=Tux Linux" ,"ou=devel","dc=example","dc=com"},
				{ "dn: cn=Tux Linux1","ou=doc",  "dc=example","dc=com"},
				{ "dn: cn=Tux Linux2","ou=doc",	 "dc=example","dc=com"},
				{ "dn: cn=Tux Linux3","ou=doc",  "dc=example","dc=com"},
				};       
		
	
		
		for(int i=0; i<data.length; i++)
		{
			
			model = (DefaultTreeModel) tree.getModel();
			
			child = new Branch(data[i]).node();
			root.add(child);
	
		}
		        
	        
		frame.add( new JScrollPane( tree ) );
			frame.pack();
			frame.setVisible( true );
		
	}
	
	
	private static int zaehler(String wasgezahltwird, String daten_all) {
		
		int zaehler=0;
		boolean eagerMatching = false;
	        
		if (0 != wasgezahltwird.length()) {
			for (int index = daten_all.indexOf(wasgezahltwird, 0); index != -1; index = daten_all
					.indexOf(wasgezahltwird, eagerMatching ? index + 1 : index + wasgezahltwird.length())) {
	            zaehler++;
	        	}
		}
		return zaehler;
	}
	
	private static void umdrehen(String[] strArray) {
		// TODO Auto-generated method stub
		
		for(String x :strArray)
			System.out.printf("%s ", x);
		System.out.println();

		//System.out.println(strArray.length);;
		
		//print out array in reverse order
		for(int i=strArray.length-1; i >= 0; i--)
		{
			//System.out.printf("%s ",strArray[i]); 
			String x12 = strArray[i];
			System.out.printf("%s ",x12);
		}
		
	}	
}



/*
function string_umdrehen() {
	String[] stuff= {"cn=Tux Linux3","ou=doc","dc=example","dc=com"};
	//print out  array
	for(String x :stuff)
		System.out.printf("%s ", x);
	System.out.println();

	//print out array in reverse order
	for(int i=stuff.length-1; i >= 0; i--)
		System.out.printf("%s ",stuff[i]);  
}
	

function buildTree(array $elements, $parentId = 0) {
    $branch = array();

    foreach ($elements as $element) {
        if ($element['parent_id'] == $parentId) {
            $children = buildTree($elements, $element['id']);
            if ($children) {
                $element['children'] = $children;
            }
            $branch[] = $element;
        }
    }

    return $branch;
}

$tree = buildTree($rows);
*/