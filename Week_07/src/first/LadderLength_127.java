package first;

import java.util.*;
import java.util.stream.Collectors;

public class LadderLength_127 {

    public static void main(String[] args) {
        LadderLength_127 ladderLength127 = new LadderLength_127();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        long start = System.currentTimeMillis();
        int result = ladderLength127.ladderLength4(beginWord, endWord, wordList);
        long end = System.currentTimeMillis();
        System.out.println(result + " " + (end - start) + "ms");
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Map<String, List<String>> map = new HashMap<>();
        Set<String> visited = new HashSet<>();

        wordList.forEach(word -> {
            for (int i = 0; i < word.length(); i++) {
                char[] array = word.toCharArray();
                array[i] = '*';
                String str = new String(array);
                List<String> list = map.getOrDefault(str, new ArrayList<>());
                list.add(word);
                map.put(str, list);
            }
        });

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String temp = queue.poll();
                for (int n = 0; n < temp.length(); n++) {
                    char[] array = temp.toCharArray();
                    array[n] = '*';
                    List<String> list = map.get(new String(array));
                    if (list == null) {
                        continue;
                    }
                    for (String word : list) {
                        if (word.equals(endWord)) {
                            return count + 2;
                        }
                        if (visited.add(word)) {
                            queue.offer(word);
                        }
                    }
                }
            }
            count++;
        }
        return 0;
    }

    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 1;
        }
        Set<String> wordSet = new HashSet<>(wordList);
        wordSet.remove(beginWord);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        Set<String> startSet = new HashSet<>();
        startSet.add(beginWord);
        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);

        int count = 1;
        while (!startSet.isEmpty() && !endSet.isEmpty()) {
            if (startSet.size() > endSet.size()) {
                Set<String> temp = endSet;
                endSet = startSet;
                startSet = temp;
            }

            Set<String> tempSet = new HashSet<>();
            for (String word : startSet) {
                char[] array = word.toCharArray();
                for (int i = 0; i < array.length; i++) {
                    char c = array[i];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (c == ch) {
                            continue;
                        }
                        array[i] = ch;
                        String str = new String(array);
                        if (endSet.contains(str)) {
                            return ++count;
                        }
                        if (wordSet.contains(str)) {
                            tempSet.add(str);
                            wordSet.remove(str);
                        }
                    }
                    array[i] = c;
                }
            }
            startSet = tempSet;
            count++;
        }
        return 0;
    }

    public int ladderLength4(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 1;
        }
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Set<String> visited = new HashSet<>();
        Map<String, List<String>> map = new HashMap<>();

        wordList.forEach(word -> {
            for (int i = 0; i < word.length(); i++) {
                char[] array = word.toCharArray();
                array[i] = '*';
                String str = new String(array);
                List<String> list = map.getOrDefault(str, new ArrayList<>());
                list.add(word);
                map.put(str, list);
            }
        });

        Set<String> startSet = new HashSet<>();
        startSet.add(beginWord);
        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);

        int count = 1;
        while (!startSet.isEmpty() && !endSet.isEmpty()) {
            if (startSet.size() > endSet.size()) {
                Set<String> temp = endSet;
                endSet = startSet;
                startSet = temp;
            }

            Set<String> tempSet = new HashSet<>();
            for (String curLevelWord : startSet) {
                for (int i = 0; i < curLevelWord.length(); i++) {
                    char[] array = curLevelWord.toCharArray();
                    array[i] = '*';
                    String str = new String(array);
                    List<String> list = map.get(str);
                    if (list == null) {
                        continue;
                    }
                    for (String nextLevelWord : list) {
                        if (endSet.contains(nextLevelWord)) {
                            return ++count;
                        }
                        if (visited.add(nextLevelWord)) {
                            tempSet.add(nextLevelWord);
                        }
                    }
                }
            }
            startSet = tempSet;
            count++;
        }
        return 0;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        wordSet.remove(beginWord);
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                if (change2(wordSet, queue, endWord)) {
                    return count + 2;
                }
            }
            count++;
        }
        return 0;
    }

    public boolean change2(Set<String> wordSet, Queue<String> queue, String endWord) {
        char[] array = queue.poll().toCharArray();
        Iterator<String> it = wordSet.iterator();
        while (it.hasNext()) {
            String word = it.next();
            int count = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] != word.charAt(i)) {
                    count++;
                }
            }
            if (count == 1) {
                if (word.equals(endWord)) {
                    return true;
                }
                queue.offer(word);
                it.remove();
            }
        }
        return false;
    }

    public boolean change(Set<String> wordSet, Queue<String> queue, String endWord) {
        List<String> list = new ArrayList<>();
        String str = queue.poll();
        char[] array = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (array[i] == ch) {
                    continue;
                }
                char temp = array[i];
                array[i] = ch;
                String tempStr = new String(array);
                if (wordSet.contains(tempStr)) {
                    list.add(tempStr);
                    wordSet.remove(tempStr);
                }
                array[i] = temp;
            }
        }

        for (String word : list) {
            if (word.equals(endWord)) {
                return true;
            }
            queue.offer(word);
        }
        return false;
    }
}
