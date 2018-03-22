package com.scarz.backend.utility;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Provides an interface to serialize data into STL strings or deserialize them into target types
 * @param <TData> Serializer type data to serialize
 */
public interface ISerializer<TData> {
    /**
     * Serialize an object into a string
     * @param data Data serialized into a string
     * @return Data serialized into a string
     * @throws Exception Thrown when serialization is not supported, or has failed
     */
    String serialize(TData data) throws Exception;

    /**
     * Deserialize string into an object
     * @param serializedData Serialized data to deserialize into target data type
     * @return Deserialized type
     * @throws Exception Thrown when deserialization is not supported, or has failed
     */
    TData deserialize(String serializedData) throws Exception;
}
