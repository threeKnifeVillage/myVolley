package com.example.myvolley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 ByteArrayPool是byte []对象的源和存储库。它的目的是
 向那些需要在短时间内使用它们然后处理它们的消费者提供这些缓冲。简单地以传统方式创建和处理这样的缓冲区可能导致Android上相当大的堆流失和垃圾收集延迟，这缺乏对短期堆对象的良好管理。以永久分配的缓冲池形式折衷一些存储器以获得堆性能改进可能是有利的。那是
 这门课做了什么。

 这个类的一个好的候选用户就像一个I / O系统，它使用大型临时byte []缓冲区来复制数据。在这些用例中，消费者通常希望缓冲区具有一定的最小尺寸以确保良好的性能（例如，当从流中复制数据块时），但是不介意缓冲区是否大于最小值。考虑到这一点并且为了最大化能够重用循环缓冲区的几率，该类可以自由地返回大于请求大小的缓冲区。调用者需要能够优雅地处理任何大小的缓冲区。如果在请求缓冲区时其回收池中没有适当大小的缓冲区，则此类将分配新缓冲区并返回它。
 这个类没有它创建的缓冲区的特殊所有权;调用者可以自由地从该池接收缓冲区，永久使用它，并且永远不会将其返回池中;另外，如果没有其他延迟引用它，则将返回到此池的缓冲区返回到其他位置是没有害处的。此类确保其回收池中缓冲区的总大小不会超过某个字节限制。当返回一个会导致池超出限制的缓冲区时，
 最近最少使用的缓冲区被丢弃。
 * </pre>
 */
public class ByteArrayPool {
    private int mCurrentSize = 0;
    private final int mSizeLimit;

    private List<byte[]> mBuffersByLastUser = new LinkedList<>();
    private List<byte[]> mBuffersBySize = new ArrayList<>();
    private static final Comparator<byte[]> BUF_COMPARATOR = new Comparator<byte[]>() {
        @Override
        public int compare(byte[] lhs, byte[] rhs) {
            return lhs.length - rhs.length;
        }
    };

    public ByteArrayPool(int sizeLimit) {
        mSizeLimit = sizeLimit;
    }

    public byte[] getBuf(int len) {
        for (int i = 0; i < mBuffersBySize.size(); i++) {
            byte[] buf = mBuffersBySize.get(i);
            if (len <= buf.length) {
                mCurrentSize -= len;
                mBuffersBySize.remove(i);
                mBuffersByLastUser.remove(buf);
                return buf;
            }
        }
        return new byte[len];
    }

    public void returnBuf(byte[] buf) {
        if (buf == null || buf.length > mSizeLimit) {
            return;
        }
        mBuffersByLastUser.add(buf);
        int pos = Collections.binarySearch(mBuffersBySize, buf, BUF_COMPARATOR);
        if (pos < 0) {
            pos = -pos - 1;
        }
        mBuffersBySize.add(pos, buf);
        mCurrentSize += buf.length;
        trim();
    }

    private void trim() {
        if (mCurrentSize > mSizeLimit) {
            byte[] buf = mBuffersByLastUser.remove(0);
            mBuffersBySize.remove(buf);
            mCurrentSize -= buf.length;
        }
    }
}
