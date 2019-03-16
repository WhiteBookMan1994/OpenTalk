package com.dxc.opentalk.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串常量池已经移到堆（Heap）中
 * @author dxc
 * @date 2018/11/6 0006
 */
public class StringConstantPoolTest {
    public static void main (String []args) {
        //防止Full GC回收常量池
        List<String> list = new ArrayList<>();
        int i =0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
/**JDK7运行环境
* Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
* */

/** JDK9运行环境
jvm参数配置：-Xmx10m
E:\JDK9\bin\java -Xmx10m "-javaagent:E:\Intellij idea\lib\idea_rt.jar=53379:E:\Intellij idea\bin" -Dfile.encoding=UTF-8 -classpath F:\OpenTalk\target\classes StringConstantPoolTest
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.base/java.util.Arrays.copyOf(Arrays.java:3719)
	at java.base/java.util.Arrays.copyOf(Arrays.java:3688)
	at java.base/java.util.ArrayList.grow(ArrayList.java:237)
	at java.base/java.util.ArrayList.grow(ArrayList.java:242)
	at java.base/java.util.ArrayList.add(ArrayList.java:467)
	at java.base/java.util.ArrayList.add(ArrayList.java:480)
	at StringConstantPoolTest.main(StringConstantPoolTest.java:14)

Process finished with exit code 1
*/

/** JDK 11 运行环境测试
 * jvm参数配置:-Xmx100m -XX:+PrintGCDetails
 E:\jdk-11.0.2\bin\java.exe -Xmx100m -XX:+PrintGCDetails "-javaagent:E:\Intellij idea\lib\idea_rt.jar=53066:E:\Intellij idea\bin" -Dfile.encoding=UTF-8 -classpath F:\OpenTalk\jvm\target\classes com.dxc.opentalk.jvm.gc.StringConstantPoolTest
 [0.012s][warning][gc] -XX:+PrintGCDetails is deprecated. Will use -Xlog:gc* instead.
 [0.031s][info   ][gc,heap] Heap region size: 1M
 [0.036s][info   ][gc     ] Using G1
 [0.036s][info   ][gc,heap,coops] Heap address: 0x00000000f9c00000, size: 100 MB, Compressed Oops mode: 32-bit
 [0.705s][info   ][gc,start     ] GC(0) Pause Young (Normal) (G1 Evacuation Pause)
 [0.706s][info   ][gc,task      ] GC(0) Using 2 workers of 4 for evacuation
 [0.754s][info   ][gc,phases    ] GC(0)   Pre Evacuate Collection Set: 0.0ms
 [0.754s][info   ][gc,phases    ] GC(0)   Evacuate Collection Set: 48.1ms
 [0.754s][info   ][gc,phases    ] GC(0)   Post Evacuate Collection Set: 0.2ms
 [0.754s][info   ][gc,phases    ] GC(0)   Other: 0.3ms
 [0.754s][info   ][gc,heap      ] GC(0) Eden regions: 14->0(6)
 [0.754s][info   ][gc,heap      ] GC(0) Survivor regions: 0->2(2)
 [0.754s][info   ][gc,heap      ] GC(0) Old regions: 0->10
 [0.754s][info   ][gc,heap      ] GC(0) Humongous regions: 2->2
 [0.754s][info   ][gc,metaspace ] GC(0) Metaspace: 6519K->6519K(1056768K)
 [0.754s][info   ][gc           ] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 16M->13M(64M) 48.660ms
 [0.754s][info   ][gc,cpu       ] GC(0) User=0.08s Sys=0.00s Real=0.05s
 [1.004s][info   ][gc,start     ] GC(1) Pause Young (Normal) (G1 Evacuation Pause)
 [1.004s][info   ][gc,task      ] GC(1) Using 2 workers of 4 for evacuation
 [1.036s][info   ][gc,phases    ] GC(1)   Pre Evacuate Collection Set: 0.0ms
 [1.036s][info   ][gc,phases    ] GC(1)   Evacuate Collection Set: 32.3ms
 [1.036s][info   ][gc,phases    ] GC(1)   Post Evacuate Collection Set: 0.1ms
 [1.036s][info   ][gc,phases    ] GC(1)   Other: 0.2ms
 [1.036s][info   ][gc,heap      ] GC(1) Eden regions: 6->0(10)
 [1.036s][info   ][gc,heap      ] GC(1) Survivor regions: 2->1(1)
 [1.036s][info   ][gc,heap      ] GC(1) Old regions: 10->17
 [1.036s][info   ][gc,heap      ] GC(1) Humongous regions: 7->7
 [1.036s][info   ][gc,metaspace ] GC(1) Metaspace: 6519K->6519K(1056768K)
 [1.036s][info   ][gc           ] GC(1) Pause Young (Normal) (G1 Evacuation Pause) 24M->25M(64M) 32.739ms
 [1.036s][info   ][gc,cpu       ] GC(1) User=0.05s Sys=0.00s Real=0.03s
 [1.616s][info   ][gc,start     ] GC(2) Pause Young (Normal) (G1 Evacuation Pause)
 [1.616s][info   ][gc,task      ] GC(2) Using 2 workers of 4 for evacuation
 [1.654s][info   ][gc,phases    ] GC(2)   Pre Evacuate Collection Set: 0.0ms
 [1.654s][info   ][gc,phases    ] GC(2)   Evacuate Collection Set: 37.8ms
 [1.654s][info   ][gc,phases    ] GC(2)   Post Evacuate Collection Set: 0.1ms
 [1.654s][info   ][gc,phases    ] GC(2)   Other: 0.1ms
 [1.654s][info   ][gc,heap      ] GC(2) Eden regions: 10->0(5)
 [1.654s][info   ][gc,heap      ] GC(2) Survivor regions: 1->2(2)
 [1.654s][info   ][gc,heap      ] GC(2) Old regions: 17->27
 [1.654s][info   ][gc,heap      ] GC(2) Humongous regions: 11->11
 [1.654s][info   ][gc,metaspace ] GC(2) Metaspace: 6519K->6519K(1056768K)
 [1.654s][info   ][gc           ] GC(2) Pause Young (Normal) (G1 Evacuation Pause) 39M->39M(64M) 38.204ms
 [1.654s][info   ][gc,cpu       ] GC(2) User=0.09s Sys=0.00s Real=0.04s
 [1.985s][info   ][gc,start     ] GC(3) Pause Young (Concurrent Start) (G1 Evacuation Pause)
 [1.985s][info   ][gc,task      ] GC(3) Using 2 workers of 4 for evacuation
 [2.011s][info   ][gc,phases    ] GC(3)   Pre Evacuate Collection Set: 0.0ms
 [2.011s][info   ][gc,phases    ] GC(3)   Evacuate Collection Set: 26.6ms
 [2.011s][info   ][gc,phases    ] GC(3)   Post Evacuate Collection Set: 0.1ms
 [2.012s][info   ][gc,phases    ] GC(3)   Other: 0.2ms
 [2.012s][info   ][gc,heap      ] GC(3) Eden regions: 5->0(3)
 [2.012s][info   ][gc,heap      ] GC(3) Survivor regions: 2->1(1)
 [2.012s][info   ][gc,heap      ] GC(3) Old regions: 27->33
 [2.012s][info   ][gc,heap      ] GC(3) Humongous regions: 11->11
 [2.012s][info   ][gc,metaspace ] GC(3) Metaspace: 6519K->6519K(1056768K)
 [2.012s][info   ][gc           ] GC(3) Pause Young (Concurrent Start) (G1 Evacuation Pause) 44M->45M(64M) 27.056ms
 [2.012s][info   ][gc,cpu       ] GC(3) User=0.06s Sys=0.00s Real=0.03s
 [2.012s][info   ][gc           ] GC(4) Concurrent Cycle
 [2.012s][info   ][gc,marking   ] GC(4) Concurrent Clear Claimed Marks
 [2.012s][info   ][gc,marking   ] GC(4) Concurrent Clear Claimed Marks 0.013ms
 [2.012s][info   ][gc,marking   ] GC(4) Concurrent Scan Root Regions
 [2.013s][info   ][gc,marking   ] GC(4) Concurrent Scan Root Regions 0.919ms
 [2.013s][info   ][gc,marking   ] GC(4) Concurrent Mark (2.013s)
 [2.013s][info   ][gc,marking   ] GC(4) Concurrent Mark From Roots
 [2.013s][info   ][gc,task      ] GC(4) Using 1 workers of 1 for marking
 [2.130s][info   ][gc,marking   ] GC(4) Concurrent Mark From Roots 117.653ms
 [2.130s][info   ][gc,marking   ] GC(4) Concurrent Preclean
 [2.130s][info   ][gc,marking   ] GC(4) Concurrent Preclean 0.066ms
 [2.130s][info   ][gc,marking   ] GC(4) Concurrent Mark (2.013s, 2.130s) 117.784ms
 [2.131s][info   ][gc,start     ] GC(4) Pause Remark
 [2.139s][info   ][gc,stringtable] GC(4) Cleaned string and symbol table, strings: 722287 processed, 3 removed, symbols: 26872 processed, 0 removed
 [2.139s][info   ][gc            ] GC(4) Pause Remark 46M->39M(64M) 8.362ms
 [2.139s][info   ][gc,cpu        ] GC(4) User=0.03s Sys=0.00s Real=0.01s
 [2.139s][info   ][gc,marking    ] GC(4) Concurrent Rebuild Remembered Sets
 [2.210s][info   ][gc,marking    ] GC(4) Concurrent Rebuild Remembered Sets 70.837ms
 [2.217s][info   ][gc,start      ] GC(4) Pause Cleanup
 [2.218s][info   ][gc            ] GC(4) Pause Cleanup 40M->40M(64M) 0.097ms
 [2.218s][info   ][gc,cpu        ] GC(4) User=0.00s Sys=0.00s Real=0.00s
 [2.218s][info   ][gc,marking    ] GC(4) Concurrent Cleanup for Next Mark
 [2.218s][info   ][gc,marking    ] GC(4) Concurrent Cleanup for Next Mark 0.109ms
 [2.218s][info   ][gc            ] GC(4) Concurrent Cycle 206.099ms
 [2.241s][info   ][gc,start      ] GC(5) Pause Young (Normal) (G1 Evacuation Pause)
 [2.242s][info   ][gc,task       ] GC(5) Using 2 workers of 4 for evacuation
 [2.255s][info   ][gc,phases     ] GC(5)   Pre Evacuate Collection Set: 0.0ms
 [2.255s][info   ][gc,phases     ] GC(5)   Evacuate Collection Set: 13.6ms
 [2.255s][info   ][gc,phases     ] GC(5)   Post Evacuate Collection Set: 0.1ms
 [2.255s][info   ][gc,phases     ] GC(5)   Other: 0.1ms
 [2.255s][info   ][gc,heap       ] GC(5) Eden regions: 3->0(4)
 [2.255s][info   ][gc,heap       ] GC(5) Survivor regions: 1->1(1)
 [2.255s][info   ][gc,heap       ] GC(5) Old regions: 33->37
 [2.255s][info   ][gc,heap       ] GC(5) Humongous regions: 4->4
 [2.255s][info   ][gc,metaspace  ] GC(5) Metaspace: 6519K->6519K(1056768K)
 [2.255s][info   ][gc            ] GC(5) Pause Young (Normal) (G1 Evacuation Pause) 41M->41M(64M) 13.906ms
 [2.255s][info   ][gc,cpu        ] GC(5) User=0.03s Sys=0.00s Real=0.01s
 [2.451s][info   ][gc,start      ] GC(6) Pause Young (Concurrent Start) (G1 Humongous Allocation)
 [2.451s][info   ][gc,task       ] GC(6) Using 2 workers of 4 for evacuation
 [2.467s][info   ][gc,phases     ] GC(6)   Pre Evacuate Collection Set: 0.0ms
 [2.467s][info   ][gc,phases     ] GC(6)   Evacuate Collection Set: 15.9ms
 [2.468s][info   ][gc,phases     ] GC(6)   Post Evacuate Collection Set: 0.1ms
 [2.468s][info   ][gc,phases     ] GC(6)   Other: 0.2ms
 [2.468s][info   ][gc,heap       ] GC(6) Eden regions: 3->0(3)
 [2.468s][info   ][gc,heap       ] GC(6) Survivor regions: 1->1(1)
 [2.468s][info   ][gc,heap       ] GC(6) Old regions: 37->40
 [2.468s][info   ][gc,heap       ] GC(6) Humongous regions: 4->4
 [2.468s][info   ][gc,metaspace  ] GC(6) Metaspace: 6519K->6519K(1056768K)
 [2.468s][info   ][gc            ] GC(6) Pause Young (Concurrent Start) (G1 Humongous Allocation) 44M->44M(64M) 16.365ms
 [2.468s][info   ][gc,cpu        ] GC(6) User=0.03s Sys=0.00s Real=0.02s
 [2.468s][info   ][gc            ] GC(7) Concurrent Cycle
 [2.468s][info   ][gc,marking    ] GC(7) Concurrent Clear Claimed Marks
 [2.468s][info   ][gc,marking    ] GC(7) Concurrent Clear Claimed Marks 0.013ms
 [2.468s][info   ][gc,marking    ] GC(7) Concurrent Scan Root Regions
 [2.469s][info   ][gc,marking    ] GC(7) Concurrent Scan Root Regions 0.916ms
 [2.469s][info   ][gc,marking    ] GC(7) Concurrent Mark (2.469s)
 [2.469s][info   ][gc,marking    ] GC(7) Concurrent Mark From Roots
 [2.469s][info   ][gc,task       ] GC(7) Using 1 workers of 1 for marking
 [2.614s][info   ][gc,marking    ] GC(7) Concurrent Mark From Roots 144.907ms
 [2.614s][info   ][gc,marking    ] GC(7) Concurrent Preclean
 [2.614s][info   ][gc,marking    ] GC(7) Concurrent Preclean 0.066ms
 [2.614s][info   ][gc,marking    ] GC(7) Concurrent Mark (2.469s, 2.614s) 145.042ms
 [2.620s][info   ][gc,start      ] GC(7) Pause Remark
 [2.629s][info   ][gc,stringtable] GC(7) Cleaned string and symbol table, strings: 845213 processed, 0 removed, symbols: 26872 processed, 0 removed
 [2.629s][info   ][gc            ] GC(7) Pause Remark 50M->50M(64M) 9.319ms
 [2.629s][info   ][gc,cpu        ] GC(7) User=0.00s Sys=0.00s Real=0.01s
 [2.629s][info   ][gc,marking    ] GC(7) Concurrent Rebuild Remembered Sets
 [2.720s][info   ][gc,marking    ] GC(7) Concurrent Rebuild Remembered Sets 90.651ms
 [2.729s][info   ][gc,start      ] GC(7) Pause Cleanup
 [2.729s][info   ][gc            ] GC(7) Pause Cleanup 52M->52M(64M) 0.096ms
 [2.729s][info   ][gc,cpu        ] GC(7) User=0.00s Sys=0.00s Real=0.00s
 [2.729s][info   ][gc,marking    ] GC(7) Concurrent Cleanup for Next Mark
 [2.729s][info   ][gc,marking    ] GC(7) Concurrent Cleanup for Next Mark 0.108ms
 [2.729s][info   ][gc            ] GC(7) Concurrent Cycle 261.415ms
 [2.754s][info   ][gc,start      ] GC(8) Pause Young (Normal) (G1 Evacuation Pause)
 [2.754s][info   ][gc,task       ] GC(8) Using 2 workers of 4 for evacuation
 [2.769s][info   ][gc,phases     ] GC(8)   Pre Evacuate Collection Set: 0.0ms
 [2.769s][info   ][gc,phases     ] GC(8)   Evacuate Collection Set: 15.0ms
 [2.769s][info   ][gc,phases     ] GC(8)   Post Evacuate Collection Set: 0.1ms
 [2.769s][info   ][gc,phases     ] GC(8)   Other: 0.1ms
 [2.769s][info   ][gc,heap       ] GC(8) Eden regions: 3->0(2)
 [2.769s][info   ][gc,heap       ] GC(8) Survivor regions: 1->1(1)
 [2.769s][info   ][gc,heap       ] GC(8) Old regions: 40->43
 [2.769s][info   ][gc,heap       ] GC(8) Humongous regions: 9->9
 [2.769s][info   ][gc,metaspace  ] GC(8) Metaspace: 6519K->6519K(1056768K)
 [2.769s][info   ][gc            ] GC(8) Pause Young (Normal) (G1 Evacuation Pause) 52M->52M(64M) 15.315ms
 [2.769s][info   ][gc,cpu        ] GC(8) User=0.03s Sys=0.00s Real=0.02s
 [2.887s][info   ][gc,start      ] GC(9) Pause Young (Concurrent Start) (G1 Evacuation Pause)
 [2.888s][info   ][gc,task       ] GC(9) Using 2 workers of 4 for evacuation
 [2.903s][info   ][gc,phases     ] GC(9)   Pre Evacuate Collection Set: 0.0ms
 [2.903s][info   ][gc,phases     ] GC(9)   Evacuate Collection Set: 15.3ms
 [2.903s][info   ][gc,phases     ] GC(9)   Post Evacuate Collection Set: 0.1ms
 [2.903s][info   ][gc,phases     ] GC(9)   Other: 0.1ms
 [2.903s][info   ][gc,heap       ] GC(9) Eden regions: 2->0(2)
 [2.903s][info   ][gc,heap       ] GC(9) Survivor regions: 1->1(1)
 [2.903s][info   ][gc,heap       ] GC(9) Old regions: 43->45
 [2.903s][info   ][gc,heap       ] GC(9) Humongous regions: 9->9
 [2.903s][info   ][gc,metaspace  ] GC(9) Metaspace: 6519K->6519K(1056768K)
 [2.903s][info   ][gc            ] GC(9) Pause Young (Concurrent Start) (G1 Evacuation Pause) 54M->54M(64M) 15.649ms
 [2.903s][info   ][gc,cpu        ] GC(9) User=0.03s Sys=0.00s Real=0.02s
 [2.903s][info   ][gc            ] GC(10) Concurrent Cycle
 [2.903s][info   ][gc,marking    ] GC(10) Concurrent Clear Claimed Marks
 [2.903s][info   ][gc,marking    ] GC(10) Concurrent Clear Claimed Marks 0.013ms
 [2.903s][info   ][gc,marking    ] GC(10) Concurrent Scan Root Regions
 [2.904s][info   ][gc,marking    ] GC(10) Concurrent Scan Root Regions 0.890ms
 [2.904s][info   ][gc,marking    ] GC(10) Concurrent Mark (2.904s)
 [2.904s][info   ][gc,marking    ] GC(10) Concurrent Mark From Roots
 [2.904s][info   ][gc,task       ] GC(10) Using 1 workers of 1 for marking
 [3.042s][info   ][gc,marking    ] GC(10) Concurrent Mark From Roots 137.461ms
 [3.042s][info   ][gc,marking    ] GC(10) Concurrent Preclean
 [3.042s][info   ][gc,marking    ] GC(10) Concurrent Preclean 0.064ms
 [3.042s][info   ][gc,marking    ] GC(10) Concurrent Mark (2.904s, 3.042s) 137.588ms
 [3.043s][info   ][gc,start      ] GC(10) Pause Remark
 [3.054s][info   ][gc,stringtable] GC(10) Cleaned string and symbol table, strings: 962403 processed, 0 removed, symbols: 26872 processed, 0 removed
 [3.054s][info   ][gc            ] GC(10) Pause Remark 56M->52M(64M) 10.829ms
 [3.054s][info   ][gc,cpu        ] GC(10) User=0.00s Sys=0.00s Real=0.01s
 [3.054s][info   ][gc,marking    ] GC(10) Concurrent Rebuild Remembered Sets
 [3.078s][info   ][gc,start      ] GC(11) Pause Young (Normal) (G1 Evacuation Pause)
 [3.078s][info   ][gc,task       ] GC(11) Using 2 workers of 4 for evacuation
 [3.091s][info   ][gc,phases     ] GC(11)   Pre Evacuate Collection Set: 0.0ms
 [3.091s][info   ][gc,phases     ] GC(11)   Evacuate Collection Set: 12.3ms
 [3.091s][info   ][gc,phases     ] GC(11)   Post Evacuate Collection Set: 0.1ms
 [3.091s][info   ][gc,phases     ] GC(11)   Other: 0.1ms
 [3.091s][info   ][gc,heap       ] GC(11) Eden regions: 2->0(2)
 [3.091s][info   ][gc,heap       ] GC(11) Survivor regions: 1->1(1)
 [3.091s][info   ][gc,heap       ] GC(11) Old regions: 45->47
 [3.091s][info   ][gc,heap       ] GC(11) Humongous regions: 5->5
 [3.091s][info   ][gc,metaspace  ] GC(11) Metaspace: 6519K->6519K(1056768K)
 [3.091s][info   ][gc            ] GC(11) Pause Young (Normal) (G1 Evacuation Pause) 52M->53M(64M) 12.616ms
 [3.091s][info   ][gc,cpu        ] GC(11) User=0.02s Sys=0.00s Real=0.01s
 [3.168s][info   ][gc,marking    ] GC(10) Concurrent Rebuild Remembered Sets 113.875ms
 [3.171s][info   ][gc,start      ] GC(10) Pause Cleanup
 [3.171s][info   ][gc            ] GC(10) Pause Cleanup 53M->53M(64M) 0.098ms
 [3.171s][info   ][gc,cpu        ] GC(10) User=0.00s Sys=0.00s Real=0.00s
 [3.171s][info   ][gc,marking    ] GC(10) Concurrent Cleanup for Next Mark
 [3.171s][info   ][gc,marking    ] GC(10) Concurrent Cleanup for Next Mark 0.115ms
 [3.171s][info   ][gc            ] GC(10) Concurrent Cycle 267.964ms
 [3.253s][info   ][gc,start      ] GC(12) Pause Young (Normal) (G1 Evacuation Pause)
 [3.253s][info   ][gc,task       ] GC(12) Using 2 workers of 4 for evacuation
 [3.268s][info   ][gc,phases     ] GC(12)   Pre Evacuate Collection Set: 0.0ms
 [3.268s][info   ][gc,phases     ] GC(12)   Evacuate Collection Set: 15.0ms
 [3.268s][info   ][gc,phases     ] GC(12)   Post Evacuate Collection Set: 0.1ms
 [3.268s][info   ][gc,phases     ] GC(12)   Other: 0.1ms
 [3.268s][info   ][gc,heap       ] GC(12) Eden regions: 2->0(2)
 [3.268s][info   ][gc,heap       ] GC(12) Survivor regions: 1->1(1)
 [3.268s][info   ][gc,heap       ] GC(12) Old regions: 47->50
 [3.268s][info   ][gc,heap       ] GC(12) Humongous regions: 5->5
 [3.268s][info   ][gc,metaspace  ] GC(12) Metaspace: 6519K->6519K(1056768K)
 [3.268s][info   ][gc            ] GC(12) Pause Young (Normal) (G1 Evacuation Pause) 55M->55M(64M) 15.344ms
 [3.268s][info   ][gc,cpu        ] GC(12) User=0.03s Sys=0.00s Real=0.02s
 [3.347s][info   ][gc,start      ] GC(13) Pause Young (Concurrent Start) (G1 Evacuation Pause)
 [3.347s][info   ][gc,task       ] GC(13) Using 2 workers of 4 for evacuation
 [3.362s][info   ][gc,phases     ] GC(13)   Pre Evacuate Collection Set: 0.0ms
 [3.362s][info   ][gc,phases     ] GC(13)   Evacuate Collection Set: 15.3ms
 [3.362s][info   ][gc,phases     ] GC(13)   Post Evacuate Collection Set: 0.1ms
 [3.363s][info   ][gc,phases     ] GC(13)   Other: 0.1ms
 [3.363s][info   ][gc,heap       ] GC(13) Eden regions: 2->0(2)
 [3.363s][info   ][gc,heap       ] GC(13) Survivor regions: 1->1(1)
 [3.363s][info   ][gc,heap       ] GC(13) Old regions: 50->52
 [3.363s][info   ][gc,heap       ] GC(13) Humongous regions: 5->5
 [3.363s][info   ][gc,metaspace  ] GC(13) Metaspace: 6519K->6519K(1056768K)
 [3.363s][info   ][gc            ] GC(13) Pause Young (Concurrent Start) (G1 Evacuation Pause) 57M->57M(64M) 15.651ms
 [3.363s][info   ][gc,cpu        ] GC(13) User=0.03s Sys=0.00s Real=0.02s
 [3.363s][info   ][gc            ] GC(14) Concurrent Cycle
 [3.363s][info   ][gc,marking    ] GC(14) Concurrent Clear Claimed Marks
 [3.363s][info   ][gc,marking    ] GC(14) Concurrent Clear Claimed Marks 0.023ms
 [3.363s][info   ][gc,marking    ] GC(14) Concurrent Scan Root Regions
 [3.364s][info   ][gc,marking    ] GC(14) Concurrent Scan Root Regions 0.903ms
 [3.364s][info   ][gc,marking    ] GC(14) Concurrent Mark (3.364s)
 [3.364s][info   ][gc,marking    ] GC(14) Concurrent Mark From Roots
 [3.364s][info   ][gc,task       ] GC(14) Using 1 workers of 1 for marking
 [3.474s][info   ][gc,start      ] GC(15) Pause Young (Normal) (G1 Evacuation Pause)
 [3.474s][info   ][gc,task       ] GC(15) Using 2 workers of 4 for evacuation
 [3.488s][info   ][gc,phases     ] GC(15)   Pre Evacuate Collection Set: 0.0ms
 [3.488s][info   ][gc,phases     ] GC(15)   Evacuate Collection Set: 13.7ms
 [3.488s][info   ][gc,phases     ] GC(15)   Post Evacuate Collection Set: 0.1ms
 [3.488s][info   ][gc,phases     ] GC(15)   Other: 0.2ms
 [3.488s][info   ][gc,heap       ] GC(15) Eden regions: 2->0(2)
 [3.488s][info   ][gc,heap       ] GC(15) Survivor regions: 1->1(1)
 [3.488s][info   ][gc,heap       ] GC(15) Old regions: 52->54
 [3.488s][info   ][gc,heap       ] GC(15) Humongous regions: 5->5
 [3.488s][info   ][gc,metaspace  ] GC(15) Metaspace: 6519K->6519K(1056768K)
 [3.488s][info   ][gc            ] GC(15) Pause Young (Normal) (G1 Evacuation Pause) 59M->59M(64M) 14.104ms
 [3.488s][info   ][gc,cpu        ] GC(15) User=0.03s Sys=0.00s Real=0.01s
 [3.560s][info   ][gc,marking    ] GC(14) Concurrent Mark From Roots 195.919ms
 [3.560s][info   ][gc,marking    ] GC(14) Concurrent Preclean
 [3.560s][info   ][gc,marking    ] GC(14) Concurrent Preclean 0.065ms
 [3.560s][info   ][gc,marking    ] GC(14) Concurrent Mark (3.364s, 3.560s) 196.046ms
 [3.561s][info   ][gc,start      ] GC(14) Pause Remark
 [3.573s][info   ][gc,stringtable] GC(14) Cleaned string and symbol table, strings: 1133953 processed, 0 removed, symbols: 26872 processed, 0 removed
 [3.573s][info   ][gc            ] GC(14) Pause Remark 61M->61M(64M) 12.475ms
 [3.573s][info   ][gc,cpu        ] GC(14) User=0.03s Sys=0.00s Real=0.01s
 [3.573s][info   ][gc,marking    ] GC(14) Concurrent Rebuild Remembered Sets
 [3.584s][info   ][gc,start      ] GC(16) Pause Young (Normal) (G1 Evacuation Pause)
 [3.584s][info   ][gc,task       ] GC(16) Using 2 workers of 4 for evacuation
 [3.598s][info   ][gc,phases     ] GC(16)   Pre Evacuate Collection Set: 0.0ms
 [3.598s][info   ][gc,phases     ] GC(16)   Evacuate Collection Set: 13.8ms
 [3.598s][info   ][gc,phases     ] GC(16)   Post Evacuate Collection Set: 0.1ms
 [3.598s][info   ][gc,phases     ] GC(16)   Other: 0.2ms
 [3.598s][info   ][gc,heap       ] GC(16) Eden regions: 2->0(2)
 [3.598s][info   ][gc,heap       ] GC(16) Survivor regions: 1->1(1)
 [3.598s][info   ][gc,heap       ] GC(16) Old regions: 54->56
 [3.598s][info   ][gc,heap       ] GC(16) Humongous regions: 5->5
 [3.598s][info   ][gc,metaspace  ] GC(16) Metaspace: 6519K->6519K(1056768K)
 [3.598s][info   ][gc            ] GC(16) Pause Young (Normal) (G1 Evacuation Pause) 61M->61M(65M) 14.184ms
 [3.598s][info   ][gc,cpu        ] GC(16) User=0.03s Sys=0.00s Real=0.01s
 [3.680s][info   ][gc,start      ] GC(17) Pause Young (Normal) (G1 Evacuation Pause)
 [3.680s][info   ][gc,task       ] GC(17) Using 2 workers of 4 for evacuation
 [3.695s][info   ][gc,phases     ] GC(17)   Pre Evacuate Collection Set: 0.0ms
 [3.695s][info   ][gc,phases     ] GC(17)   Evacuate Collection Set: 15.1ms
 [3.695s][info   ][gc,phases     ] GC(17)   Post Evacuate Collection Set: 0.1ms
 [3.695s][info   ][gc,phases     ] GC(17)   Other: 0.1ms
 [3.695s][info   ][gc,heap       ] GC(17) Eden regions: 2->0(2)
 [3.695s][info   ][gc,heap       ] GC(17) Survivor regions: 1->1(1)
 [3.695s][info   ][gc,heap       ] GC(17) Old regions: 56->58
 [3.695s][info   ][gc,heap       ] GC(17) Humongous regions: 5->5
 [3.695s][info   ][gc,metaspace  ] GC(17) Metaspace: 6519K->6519K(1056768K)
 [3.695s][info   ][gc            ] GC(17) Pause Young (Normal) (G1 Evacuation Pause) 63M->63M(67M) 15.436ms
 [3.695s][info   ][gc,cpu        ] GC(17) User=0.03s Sys=0.00s Real=0.02s
 [3.723s][info   ][gc,marking    ] GC(14) Concurrent Rebuild Remembered Sets 149.847ms
 [3.726s][info   ][gc,start      ] GC(14) Pause Cleanup
 [3.726s][info   ][gc            ] GC(14) Pause Cleanup 64M->64M(67M) 0.110ms
 [3.726s][info   ][gc,cpu        ] GC(14) User=0.00s Sys=0.00s Real=0.00s
 [3.727s][info   ][gc,marking    ] GC(14) Concurrent Cleanup for Next Mark
 [3.727s][info   ][gc,marking    ] GC(14) Concurrent Cleanup for Next Mark 0.208ms
 [3.727s][info   ][gc            ] GC(14) Concurrent Cycle 364.083ms
 [3.779s][info   ][gc,start      ] GC(18) Pause Young (Concurrent Start) (G1 Humongous Allocation)
 [3.779s][info   ][gc,task       ] GC(18) Using 2 workers of 4 for evacuation
 [3.796s][info   ][gc,phases     ] GC(18)   Pre Evacuate Collection Set: 0.0ms
 [3.796s][info   ][gc,phases     ] GC(18)   Evacuate Collection Set: 16.4ms
 [3.796s][info   ][gc,phases     ] GC(18)   Post Evacuate Collection Set: 0.1ms
 [3.796s][info   ][gc,phases     ] GC(18)   Other: 0.2ms
 [3.796s][info   ][gc,heap       ] GC(18) Eden regions: 2->0(2)
 [3.796s][info   ][gc,heap       ] GC(18) Survivor regions: 1->1(1)
 [3.796s][info   ][gc,heap       ] GC(18) Old regions: 58->60
 [3.796s][info   ][gc,heap       ] GC(18) Humongous regions: 5->5
 [3.796s][info   ][gc,metaspace  ] GC(18) Metaspace: 6519K->6519K(1056768K)
 [3.796s][info   ][gc            ] GC(18) Pause Young (Concurrent Start) (G1 Humongous Allocation) 65M->65M(69M) 16.764ms
 [3.796s][info   ][gc,cpu        ] GC(18) User=0.03s Sys=0.00s Real=0.02s
 [3.796s][info   ][gc            ] GC(19) Concurrent Cycle
 [3.796s][info   ][gc,marking    ] GC(19) Concurrent Clear Claimed Marks
 [3.796s][info   ][gc,marking    ] GC(19) Concurrent Clear Claimed Marks 0.013ms
 [3.796s][info   ][gc,marking    ] GC(19) Concurrent Scan Root Regions
 [3.797s][info   ][gc,marking    ] GC(19) Concurrent Scan Root Regions 0.852ms
 [3.797s][info   ][gc,marking    ] GC(19) Concurrent Mark (3.797s)
 [3.797s][info   ][gc,marking    ] GC(19) Concurrent Mark From Roots
 [3.797s][info   ][gc,task       ] GC(19) Using 1 workers of 1 for marking
 [3.890s][info   ][gc,start      ] GC(20) Pause Young (Normal) (G1 Evacuation Pause)
 [3.890s][info   ][gc,task       ] GC(20) Using 2 workers of 4 for evacuation
 [3.907s][info   ][gc,phases     ] GC(20)   Pre Evacuate Collection Set: 0.0ms
 [3.907s][info   ][gc,phases     ] GC(20)   Evacuate Collection Set: 16.7ms
 [3.907s][info   ][gc,phases     ] GC(20)   Post Evacuate Collection Set: 0.1ms
 [3.907s][info   ][gc,phases     ] GC(20)   Other: 0.1ms
 [3.907s][info   ][gc,heap       ] GC(20) Eden regions: 2->0(2)
 [3.907s][info   ][gc,heap       ] GC(20) Survivor regions: 1->1(1)
 [3.907s][info   ][gc,heap       ] GC(20) Old regions: 60->62
 [3.907s][info   ][gc,heap       ] GC(20) Humongous regions: 12->12
 [3.907s][info   ][gc,metaspace  ] GC(20) Metaspace: 6519K->6519K(1056768K)
 [3.907s][info   ][gc            ] GC(20) Pause Young (Normal) (G1 Evacuation Pause) 74M->74M(78M) 17.088ms
 [3.907s][info   ][gc,cpu        ] GC(20) User=0.03s Sys=0.00s Real=0.02s
 [4.006s][info   ][gc,start      ] GC(21) Pause Young (Normal) (G1 Evacuation Pause)
 [4.006s][info   ][gc,task       ] GC(21) Using 2 workers of 4 for evacuation
 [4.023s][info   ][gc,phases     ] GC(21)   Pre Evacuate Collection Set: 0.0ms
 [4.023s][info   ][gc,phases     ] GC(21)   Evacuate Collection Set: 16.8ms
 [4.023s][info   ][gc,phases     ] GC(21)   Post Evacuate Collection Set: 0.1ms
 [4.023s][info   ][gc,phases     ] GC(21)   Other: 0.1ms
 [4.023s][info   ][gc,heap       ] GC(21) Eden regions: 2->0(3)
 [4.023s][info   ][gc,heap       ] GC(21) Survivor regions: 1->1(1)
 [4.023s][info   ][gc,heap       ] GC(21) Old regions: 62->64
 [4.023s][info   ][gc,heap       ] GC(21) Humongous regions: 12->12
 [4.023s][info   ][gc,metaspace  ] GC(21) Metaspace: 6519K->6519K(1056768K)
 [4.023s][info   ][gc            ] GC(21) Pause Young (Normal) (G1 Evacuation Pause) 76M->76M(80M) 17.210ms
 [4.023s][info   ][gc,cpu        ] GC(21) User=0.03s Sys=0.00s Real=0.02s
 [4.028s][info   ][gc,marking    ] GC(19) Concurrent Mark From Roots 230.707ms
 [4.028s][info   ][gc,marking    ] GC(19) Concurrent Preclean
 [4.028s][info   ][gc,marking    ] GC(19) Concurrent Preclean 0.066ms
 [4.028s][info   ][gc,marking    ] GC(19) Concurrent Mark (3.797s, 4.028s) 230.841ms
 [4.035s][info   ][gc,start      ] GC(19) Pause Remark
 [4.049s][info   ][gc,stringtable] GC(19) Cleaned string and symbol table, strings: 1307293 processed, 0 removed, symbols: 26872 processed, 0 removed
 [4.050s][info   ][gc            ] GC(19) Pause Remark 77M->77M(80M) 14.794ms
 [4.050s][info   ][gc,cpu        ] GC(19) User=0.03s Sys=0.00s Real=0.02s
 [4.050s][info   ][gc,marking    ] GC(19) Concurrent Rebuild Remembered Sets
 [4.160s][info   ][gc,start      ] GC(22) Pause Young (Normal) (G1 Evacuation Pause)
 [4.160s][info   ][gc,task       ] GC(22) Using 2 workers of 4 for evacuation
 [4.181s][info   ][gc,phases     ] GC(22)   Pre Evacuate Collection Set: 0.0ms
 [4.182s][info   ][gc,phases     ] GC(22)   Evacuate Collection Set: 21.6ms
 [4.182s][info   ][gc,phases     ] GC(22)   Post Evacuate Collection Set: 0.2ms
 [4.182s][info   ][gc,phases     ] GC(22)   Other: 0.1ms
 [4.182s][info   ][gc,heap       ] GC(22) Eden regions: 3->0(3)
 [4.182s][info   ][gc,heap       ] GC(22) Survivor regions: 1->1(1)
 [4.182s][info   ][gc,heap       ] GC(22) Old regions: 64->67
 [4.182s][info   ][gc,heap       ] GC(22) Humongous regions: 12->12
 [4.182s][info   ][gc,metaspace  ] GC(22) Metaspace: 6519K->6519K(1056768K)
 [4.182s][info   ][gc            ] GC(22) Pause Young (Normal) (G1 Evacuation Pause) 79M->80M(86M) 22.081ms
 [4.182s][info   ][gc,cpu        ] GC(22) User=0.06s Sys=0.00s Real=0.03s
 [4.222s][info   ][gc,marking    ] GC(19) Concurrent Rebuild Remembered Sets 172.189ms
 [4.225s][info   ][gc,start      ] GC(19) Pause Cleanup
 [4.225s][info   ][gc            ] GC(19) Pause Cleanup 81M->81M(86M) 0.119ms
 [4.225s][info   ][gc,cpu        ] GC(19) User=0.00s Sys=0.00s Real=0.00s
 [4.225s][info   ][gc,marking    ] GC(19) Concurrent Cleanup for Next Mark
 [4.226s][info   ][gc,marking    ] GC(19) Concurrent Cleanup for Next Mark 0.210ms
 [4.226s][info   ][gc            ] GC(19) Concurrent Cycle 429.717ms
 [4.310s][info   ][gc,start      ] GC(23) Pause Young (Normal) (G1 Evacuation Pause)
 [4.310s][info   ][gc,task       ] GC(23) Using 2 workers of 4 for evacuation
 [4.329s][info   ][gc,phases     ] GC(23)   Pre Evacuate Collection Set: 0.0ms
 [4.329s][info   ][gc,phases     ] GC(23)   Evacuate Collection Set: 18.7ms
 [4.329s][info   ][gc,phases     ] GC(23)   Post Evacuate Collection Set: 0.1ms
 [4.329s][info   ][gc,phases     ] GC(23)   Other: 0.2ms
 [4.329s][info   ][gc,heap       ] GC(23) Eden regions: 3->0(3)
 [4.329s][info   ][gc,heap       ] GC(23) Survivor regions: 1->1(1)
 [4.329s][info   ][gc,heap       ] GC(23) Old regions: 67->71
 [4.329s][info   ][gc,heap       ] GC(23) Humongous regions: 12->12
 [4.329s][info   ][gc,metaspace  ] GC(23) Metaspace: 6519K->6519K(1056768K)
 [4.329s][info   ][gc            ] GC(23) Pause Young (Normal) (G1 Evacuation Pause) 83M->83M(88M) 19.097ms
 [4.329s][info   ][gc,cpu        ] GC(23) User=0.03s Sys=0.00s Real=0.02s
 [4.471s][info   ][gc,start      ] GC(24) Pause Young (Concurrent Start) (G1 Evacuation Pause)
 [4.471s][info   ][gc,task       ] GC(24) Using 2 workers of 4 for evacuation
 [4.502s][info   ][gc,phases     ] GC(24)   Pre Evacuate Collection Set: 0.0ms
 [4.502s][info   ][gc,phases     ] GC(24)   Evacuate Collection Set: 30.0ms
 [4.502s][info   ][gc,phases     ] GC(24)   Post Evacuate Collection Set: 0.1ms
 [4.502s][info   ][gc,phases     ] GC(24)   Other: 0.2ms
 [4.502s][info   ][gc,heap       ] GC(24) Eden regions: 3->0(3)
 [4.502s][info   ][gc,heap       ] GC(24) Survivor regions: 1->1(1)
 [4.502s][info   ][gc,heap       ] GC(24) Old regions: 71->74
 [4.502s][info   ][gc,heap       ] GC(24) Humongous regions: 12->12
 [4.502s][info   ][gc,metaspace  ] GC(24) Metaspace: 6519K->6519K(1056768K)
 [4.502s][info   ][gc            ] GC(24) Pause Young (Concurrent Start) (G1 Evacuation Pause) 86M->86M(91M) 30.546ms
 [4.502s][info   ][gc,cpu        ] GC(24) User=0.06s Sys=0.00s Real=0.03s
 [4.502s][info   ][gc            ] GC(25) Concurrent Cycle
 [4.502s][info   ][gc,marking    ] GC(25) Concurrent Clear Claimed Marks
 [4.502s][info   ][gc,marking    ] GC(25) Concurrent Clear Claimed Marks 0.016ms
 [4.502s][info   ][gc,marking    ] GC(25) Concurrent Scan Root Regions
 [4.503s][info   ][gc,marking    ] GC(25) Concurrent Scan Root Regions 0.828ms
 [4.503s][info   ][gc,marking    ] GC(25) Concurrent Mark (4.503s)
 [4.503s][info   ][gc,marking    ] GC(25) Concurrent Mark From Roots
 [4.503s][info   ][gc,task       ] GC(25) Using 1 workers of 1 for marking
 [4.633s][info   ][gc,start      ] GC(26) Pause Young (Normal) (G1 Evacuation Pause)
 [4.633s][info   ][gc,task       ] GC(26) Using 2 workers of 4 for evacuation
 [4.655s][info   ][gc,phases     ] GC(26)   Pre Evacuate Collection Set: 0.0ms
 [4.655s][info   ][gc,phases     ] GC(26)   Evacuate Collection Set: 21.7ms
 [4.655s][info   ][gc,phases     ] GC(26)   Post Evacuate Collection Set: 0.1ms
 [4.655s][info   ][gc,phases     ] GC(26)   Other: 0.1ms
 [4.655s][info   ][gc,heap       ] GC(26) Eden regions: 3->0(3)
 [4.655s][info   ][gc,heap       ] GC(26) Survivor regions: 1->1(1)
 [4.655s][info   ][gc,heap       ] GC(26) Old regions: 74->77
 [4.655s][info   ][gc,heap       ] GC(26) Humongous regions: 12->12
 [4.655s][info   ][gc,metaspace  ] GC(26) Metaspace: 6519K->6519K(1056768K)
 [4.655s][info   ][gc            ] GC(26) Pause Young (Normal) (G1 Evacuation Pause) 89M->89M(94M) 22.082ms
 [4.655s][info   ][gc,cpu        ] GC(26) User=0.03s Sys=0.00s Real=0.02s
 [4.780s][info   ][gc,start      ] GC(27) Pause Young (Normal) (G1 Evacuation Pause)
 [4.780s][info   ][gc,task       ] GC(27) Using 2 workers of 4 for evacuation
 [4.808s][info   ][gc,phases     ] GC(27)   Pre Evacuate Collection Set: 0.0ms
 [4.808s][info   ][gc,phases     ] GC(27)   Evacuate Collection Set: 28.4ms
 [4.808s][info   ][gc,phases     ] GC(27)   Post Evacuate Collection Set: 0.1ms
 [4.808s][info   ][gc,phases     ] GC(27)   Other: 0.2ms
 [4.808s][info   ][gc,heap       ] GC(27) Eden regions: 3->0(3)
 [4.808s][info   ][gc,heap       ] GC(27) Survivor regions: 1->1(1)
 [4.808s][info   ][gc,heap       ] GC(27) Old regions: 77->80
 [4.808s][info   ][gc,heap       ] GC(27) Humongous regions: 12->12
 [4.808s][info   ][gc,metaspace  ] GC(27) Metaspace: 6519K->6519K(1056768K)
 [4.808s][info   ][gc            ] GC(27) Pause Young (Normal) (G1 Evacuation Pause) 92M->92M(98M) 28.822ms
 [4.808s][info   ][gc,cpu        ] GC(27) User=0.03s Sys=0.03s Real=0.03s
 [4.842s][info   ][gc,marking    ] GC(25) Concurrent Mark From Roots 339.467ms
 [4.842s][info   ][gc,marking    ] GC(25) Concurrent Preclean
 [4.843s][info   ][gc,marking    ] GC(25) Concurrent Preclean 0.064ms
 [4.843s][info   ][gc,marking    ] GC(25) Concurrent Mark (4.503s, 4.843s) 339.597ms
 [4.851s][info   ][gc,start      ] GC(25) Pause Remark
 [4.869s][info   ][gc,stringtable] GC(25) Cleaned string and symbol table, strings: 1647041 processed, 0 removed, symbols: 26872 processed, 0 removed
 [4.870s][info   ][gc            ] GC(25) Pause Remark 93M->88M(98M) 18.174ms
 [4.870s][info   ][gc,cpu        ] GC(25) User=0.06s Sys=0.00s Real=0.02s
 [4.870s][info   ][gc,marking    ] GC(25) Concurrent Rebuild Remembered Sets
 [4.965s][info   ][gc,start      ] GC(28) Pause Young (Normal) (G1 Evacuation Pause)
 [4.965s][info   ][gc,task       ] GC(28) Using 2 workers of 4 for evacuation
 [4.991s][info   ][gc,phases     ] GC(28)   Pre Evacuate Collection Set: 0.0ms
 [4.991s][info   ][gc,phases     ] GC(28)   Evacuate Collection Set: 25.6ms
 [4.991s][info   ][gc,phases     ] GC(28)   Post Evacuate Collection Set: 0.1ms
 [4.991s][info   ][gc,phases     ] GC(28)   Other: 0.1ms
 [4.991s][info   ][gc,heap       ] GC(28) Eden regions: 3->0(3)
 [4.991s][info   ][gc,heap       ] GC(28) Survivor regions: 1->1(1)
 [4.991s][info   ][gc,heap       ] GC(28) Old regions: 80->84
 [4.991s][info   ][gc,heap       ] GC(28) Humongous regions: 7->7
 [4.991s][info   ][gc,metaspace  ] GC(28) Metaspace: 6519K->6519K(1056768K)
 [4.991s][info   ][gc            ] GC(28) Pause Young (Normal) (G1 Evacuation Pause) 90M->91M(98M) 25.992ms
 [4.991s][info   ][gc,cpu        ] GC(28) User=0.03s Sys=0.00s Real=0.03s
 [5.085s][info   ][gc,marking    ] GC(25) Concurrent Rebuild Remembered Sets 215.562ms
 [5.091s][info   ][gc,start      ] GC(25) Pause Cleanup
 [5.091s][info   ][gc            ] GC(25) Pause Cleanup 93M->93M(98M) 0.134ms
 [5.091s][info   ][gc,cpu        ] GC(25) User=0.00s Sys=0.00s Real=0.00s
 [5.091s][info   ][gc,marking    ] GC(25) Concurrent Cleanup for Next Mark
 [5.092s][info   ][gc,marking    ] GC(25) Concurrent Cleanup for Next Mark 0.206ms
 [5.092s][info   ][gc            ] GC(25) Concurrent Cycle 589.528ms
 [5.133s][info   ][gc,start      ] GC(29) Pause Young (Normal) (G1 Evacuation Pause)
 [5.133s][info   ][gc,task       ] GC(29) Using 2 workers of 4 for evacuation
 [5.153s][info   ][gc,phases     ] GC(29)   Pre Evacuate Collection Set: 0.0ms
 [5.153s][info   ][gc,phases     ] GC(29)   Evacuate Collection Set: 19.8ms
 [5.153s][info   ][gc,phases     ] GC(29)   Post Evacuate Collection Set: 0.1ms
 [5.153s][info   ][gc,phases     ] GC(29)   Other: 0.1ms
 [5.153s][info   ][gc,heap       ] GC(29) Eden regions: 3->0(3)
 [5.153s][info   ][gc,heap       ] GC(29) Survivor regions: 1->1(1)
 [5.153s][info   ][gc,heap       ] GC(29) Old regions: 84->87
 [5.153s][info   ][gc,heap       ] GC(29) Humongous regions: 7->7
 [5.153s][info   ][gc,metaspace  ] GC(29) Metaspace: 6519K->6519K(1056768K)
 [5.153s][info   ][gc            ] GC(29) Pause Young (Normal) (G1 Evacuation Pause) 94M->94M(99M) 20.193ms
 [5.153s][info   ][gc,cpu        ] GC(29) User=0.03s Sys=0.00s Real=0.02s
 [5.261s][info   ][gc,start      ] GC(30) Pause Young (Concurrent Start) (G1 Evacuation Pause)
 [5.261s][info   ][gc,task       ] GC(30) Using 2 workers of 4 for evacuation
 [5.292s][info   ][gc            ] GC(30) To-space exhausted
 [5.292s][info   ][gc,phases     ] GC(30)   Pre Evacuate Collection Set: 0.0ms
 [5.292s][info   ][gc,phases     ] GC(30)   Evacuate Collection Set: 23.2ms
 [5.292s][info   ][gc,phases     ] GC(30)   Post Evacuate Collection Set: 7.0ms
 [5.292s][info   ][gc,phases     ] GC(30)   Other: 0.2ms
 [5.292s][info   ][gc,heap       ] GC(30) Eden regions: 3->0(4)
 [5.292s][info   ][gc,heap       ] GC(30) Survivor regions: 1->1(1)
 [5.292s][info   ][gc,heap       ] GC(30) Old regions: 87->91
 [5.292s][info   ][gc,heap       ] GC(30) Humongous regions: 7->7
 [5.292s][info   ][gc,metaspace  ] GC(30) Metaspace: 6519K->6519K(1056768K)
 [5.292s][info   ][gc            ] GC(30) Pause Young (Concurrent Start) (G1 Evacuation Pause) 97M->99M(100M) 30.528ms
 [5.292s][info   ][gc,cpu        ] GC(30) User=0.05s Sys=0.00s Real=0.03s
 [5.292s][info   ][gc            ] GC(31) Concurrent Cycle
 [5.292s][info   ][gc,marking    ] GC(31) Concurrent Clear Claimed Marks
 [5.292s][info   ][gc,marking    ] GC(31) Concurrent Clear Claimed Marks 0.014ms
 [5.292s][info   ][gc,marking    ] GC(31) Concurrent Scan Root Regions
 [5.293s][info   ][gc,marking    ] GC(31) Concurrent Scan Root Regions 0.871ms
 [5.293s][info   ][gc,marking    ] GC(31) Concurrent Mark (5.293s)
 [5.293s][info   ][gc,marking    ] GC(31) Concurrent Mark From Roots
 [5.293s][info   ][gc,task       ] GC(31) Using 1 workers of 1 for marking
 [5.301s][info   ][gc,start      ] GC(32) Pause Young (Normal) (G1 Humongous Allocation)
 [5.301s][info   ][gc,task       ] GC(32) Using 2 workers of 4 for evacuation
 [5.319s][info   ][gc            ] GC(32) To-space exhausted
 [5.319s][info   ][gc,phases     ] GC(32)   Pre Evacuate Collection Set: 0.0ms
 [5.319s][info   ][gc,phases     ] GC(32)   Evacuate Collection Set: 14.2ms
 [5.319s][info   ][gc,phases     ] GC(32)   Post Evacuate Collection Set: 3.1ms
 [5.319s][info   ][gc,phases     ] GC(32)   Other: 0.2ms
 [5.319s][info   ][gc,heap       ] GC(32) Eden regions: 1->0(5)
 [5.319s][info   ][gc,heap       ] GC(32) Survivor regions: 1->0(1)
 [5.319s][info   ][gc,heap       ] GC(32) Old regions: 91->93
 [5.319s][info   ][gc,heap       ] GC(32) Humongous regions: 7->7
 [5.319s][info   ][gc,metaspace  ] GC(32) Metaspace: 6519K->6519K(1056768K)
 [5.319s][info   ][gc            ] GC(32) Pause Young (Normal) (G1 Humongous Allocation) 99M->99M(100M) 17.584ms
 [5.319s][info   ][gc,cpu        ] GC(32) User=0.03s Sys=0.00s Real=0.02s
 [5.320s][info   ][gc,task       ] GC(33) Using 2 workers of 4 for full compaction
 [5.320s][info   ][gc,start      ] GC(33) Pause Full (G1 Humongous Allocation)
 [5.320s][info   ][gc,phases,start] GC(33) Phase 1: Mark live objects
 [5.480s][info   ][gc,stringtable ] GC(33) Cleaned string and symbol table, strings: 1826126 processed, 2 removed, symbols: 26872 processed, 0 removed
 [5.481s][info   ][gc,phases      ] GC(33) Phase 1: Mark live objects 160.546ms
 [5.481s][info   ][gc,phases,start] GC(33) Phase 2: Prepare for compaction
 [5.518s][info   ][gc,phases      ] GC(33) Phase 2: Prepare for compaction 37.614ms
 [5.518s][info   ][gc,phases,start] GC(33) Phase 3: Adjust pointers
 [5.574s][info   ][gc,phases      ] GC(33) Phase 3: Adjust pointers 55.342ms
 [5.574s][info   ][gc,phases,start] GC(33) Phase 4: Compact heap
 [5.609s][info   ][gc,phases      ] GC(33) Phase 4: Compact heap 35.102ms
 [5.609s][info   ][gc,heap        ] GC(33) Eden regions: 0->0(5)
 [5.609s][info   ][gc,heap        ] GC(33) Survivor regions: 0->0(1)
 [5.609s][info   ][gc,heap        ] GC(33) Old regions: 93->86
 [5.609s][info   ][gc,heap        ] GC(33) Humongous regions: 7->7
 [5.609s][info   ][gc,metaspace   ] GC(33) Metaspace: 6519K->6519K(1056768K)
 [5.609s][info   ][gc             ] GC(33) Pause Full (G1 Humongous Allocation) 99M->91M(100M) 289.384ms
 [5.609s][info   ][gc,cpu         ] GC(33) User=0.59s Sys=0.00s Real=0.29s
 [5.609s][info   ][gc,task        ] GC(34) Using 2 workers of 4 for full compaction
 [5.609s][info   ][gc,start       ] GC(34) Pause Full (G1 Humongous Allocation)
 [5.609s][info   ][gc,phases,start] GC(34) Phase 1: Mark live objects
 [5.758s][info   ][gc,stringtable ] GC(34) Cleaned string and symbol table, strings: 1826124 processed, 0 removed, symbols: 26872 processed, 0 removed
 [5.758s][info   ][gc,phases      ] GC(34) Phase 1: Mark live objects 148.811ms
 [5.758s][info   ][gc,phases,start] GC(34) Phase 2: Prepare for compaction
 [5.793s][info   ][gc,phases      ] GC(34) Phase 2: Prepare for compaction 34.810ms
 [5.793s][info   ][gc,phases,start] GC(34) Phase 3: Adjust pointers
 [5.847s][info   ][gc,phases      ] GC(34) Phase 3: Adjust pointers 53.706ms
 [5.847s][info   ][gc,phases,start] GC(34) Phase 4: Compact heap
 [5.869s][info   ][gc,phases      ] GC(34) Phase 4: Compact heap 22.048ms
 [5.869s][info   ][gc,heap        ] GC(34) Eden regions: 0->0(5)
 [5.869s][info   ][gc,heap        ] GC(34) Survivor regions: 0->0(1)
 [5.869s][info   ][gc,heap        ] GC(34) Old regions: 86->85
 [5.869s][info   ][gc,heap        ] GC(34) Humongous regions: 7->7
 [5.869s][info   ][gc,metaspace   ] GC(34) Metaspace: 6519K->6519K(1056768K)
 [5.869s][info   ][gc             ] GC(34) Pause Full (G1 Humongous Allocation) 91M->91M(100M) 260.123ms
 [5.870s][info   ][gc,cpu         ] GC(34) User=0.53s Sys=0.00s Real=0.26s
 [5.870s][info   ][gc,marking     ] GC(31) Concurrent Mark From Roots 576.931ms
 [5.870s][info   ][gc,marking     ] GC(31) Concurrent Mark Abort
 [5.870s][info   ][gc             ] GC(31) Concurrent Cycle 577.927ms
 Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 at java.base/java.util.Arrays.copyOf(Arrays.java:3720)
 at java.base/java.util.Arrays.copyOf(Arrays.java:3689)
 at java.base/java.util.ArrayList.grow(ArrayList.java:237)
 at java.base/java.util.ArrayList.grow(ArrayList.java:242)
 at java.base/java.util.ArrayList.add(ArrayList.java:485)
 at java.base/java.util.ArrayList.add(ArrayList.java:498)
 at com.dxc.opentalk.jvm.gc.StringConstantPoolTest.main(StringConstantPoolTest.java:16)
 [5.874s][info   ][gc,heap,exit   ] Heap
 [5.874s][info   ][gc,heap,exit   ]  garbage-first heap   total 102400K, used 93600K [0x00000000f9c00000, 0x0000000100000000)
 [5.874s][info   ][gc,heap,exit   ]   region size 1024K, 1 young (1024K), 0 survivors (0K)
 [5.874s][info   ][gc,heap,exit   ]  Metaspace       used 6543K, capacity 6607K, committed 6784K, reserved 1056768K
 [5.874s][info   ][gc,heap,exit   ]   class space    used 576K, capacity 602K, committed 640K, reserved 1048576K

 Process finished with exit code 1
 * */