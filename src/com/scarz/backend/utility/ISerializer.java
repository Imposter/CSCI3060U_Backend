package com.scarz.backend.utility;

/**
 * Provides an interface to serialize data into STL strings or deserialize them into target types
 * @param <TData> Serializer type data to serialize
 */
public interface ISerializer<TData> {
    /**
     * Serialize an object into a string
     * @param data Data serialized into a string
     * @return Data serialized into a string
     * @throws UnsupportedOperationException Thrown when serialization is not supported
     */
    String serialize(TData data) throws UnsupportedOperationException;

    /**
     * Deserialize string into an object
     * @param serializedData Serialized data to deserialize into target data type
     * @return Deserialized type
     * @throws UnsupportedOperationException Thrown when deserialization is not supported
     */
    TData deserialize(String serializedData) throws UnsupportedOperationException;
}
