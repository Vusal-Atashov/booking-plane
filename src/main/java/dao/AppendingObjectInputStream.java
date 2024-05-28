package dao;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class AppendingObjectInputStream extends ObjectInputStream {

    public AppendingObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected void readStreamHeader() throws IOException {
        // Do nothing to avoid reading a header in the middle of the stream
    }

    @Override
    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        return super.readClassDescriptor();
    }
}
