package com.jinzhun.common.algorithm.smatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TrieTreeSolution {
	static class Node {
		char c;
		boolean isEnd;
		List<Node> children;
		public Node(char c) {
			super();
			this.c = c;
			isEnd = false;
			children = new LinkedList<Node>();
		}

		public Node findNode(char c) {
			for (Node node : children) {
				if (node.c == c) {
					return node;
				}
			}
			return null;
		}
	}

	static class TrieTree {
		Node root = new Node(' ');

		// 构建Tire Tree
		public void insert(String words) {
			char[] arr = words.toCharArray();
			Node current = root;
			for (char c : arr) {
				Node node = current.findNode(c);
				// 如果不存在该节点则添加
				if (node == null) {
					Node n = new Node(c);
					current.children.add(n);
					current = n;
				} else {
					current = node;
				}
			}
			current.isEnd = true;
		}

		public boolean search(String word) {
			char[] arr = word.toCharArray();
			Node current = root;
			for (int i = 0; i < arr.length; i++) {
				Node n = current.findNode(arr[i]);
				if (n != null) {
					current = n;
					// 判断是否为词的尾部节点
					if (n.isEnd) {
						if (n.c == arr[arr.length - 1]) {
							return true;
						}
					}
				}
			}
			return false;
		}

		// 最大匹配优先原则
		public Map<String, Integer> tokenizer(String words) {
			char[] arr = words.toCharArray();
			Node current = root;
			Map<String, Integer> map = new HashMap<String, Integer>();
			// 记录Trie Tree 从root开始匹配的所有字
			StringBuilder sb = new StringBuilder();
			// 最后一次匹配到的词，最大匹配原则，可能会匹配到多个字，以最长的那个为准
			String word = "";
			// 记录记录最后一次匹配坐标
			int idx = 0;
			for (int i = 0; i < arr.length; i++) {
				Node n = current.findNode(arr[i]);
				if (n != null) {
					sb.append(n.c);
					current = n;
					// 匹配到词
					if (n.isEnd) {
						// 记录最后一次匹配的词
						word = sb.toString();
						// 记录最后一次匹配坐标
						idx = i;
					}
				} else {
					// 判断word是否有值
					if (word != null && word.length() > 0) {
						Integer num = map.get(word);
						if (num == null) {
							map.put(word, 1);
						} else {
							map.put(word, num + 1);
						}
						// i回退到最后匹配的坐标
						i = idx;
						// 从root的开始匹配
						current = root;
						// 清空匹配到的词
						word = null;
						// 清空当前路径匹配到的所有字
						sb = new StringBuilder();
					}
				}
				// 已匹配到最后一位
				if (i == arr.length - 1) {
					if (word != null && word.length() > 0) {
						Integer num = map.get(word);
						if (num == null) {
							map.put(word, 1);
						} else {
							map.put(word, num + 1);
						}
					}
				}
			}

			return map;
		}
	}
	
    public static void main(String[] args) {
        TrieTree tree = new TrieTree();
        tree.insert("华南师范大学");
        tree.insert("华南师范大学南门");
        tree.insert("华南师范大学正门");
        
        tree.insert("华艺");
        tree.insert("华忆百货");
        
        String word = "华艺";
        boolean flag = tree.search(word);
        if(flag){
            System.out.println("Trie Tree 中已经存在【"+word+"】");
        }else{
            System.out.println("Trie Tree 不包含【"+word+"】");
        }
        
        //分词
        Map<String, Integer> map = tree.tokenizer("华南师范大学，华南师范大学南门，华南师范大学正门");
        for (Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        
    }
}
