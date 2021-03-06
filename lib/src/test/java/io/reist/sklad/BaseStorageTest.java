/*
 * Copyright (C) 2017 Renat Sarymsakov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.reist.sklad;

import android.support.annotation.NonNull;

import org.junit.Test;

import java.io.IOException;

import static io.reist.sklad.TestUtils.assertTestObject;
import static io.reist.sklad.TestUtils.saveTestObject;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Reist on 28.06.16.
 */
public abstract class BaseStorageTest<S extends Storage> {

    @Test
    public void testContains() throws Exception {
        S storage = createStorage();
        assertFalse(storage.contains(TestUtils.TEST_NAME));
        saveTestObject(storage);
        assertTrue(storage.contains(TestUtils.TEST_NAME));
    }

    @Test
    public void testStreams() throws Exception {
        S storage = createStorage();
        saveTestObject(storage);
        assertTestObject(storage);
    }

    @NonNull
    protected abstract S createStorage() throws IOException;

    @Test
    public void testDelete() throws Exception {
        S storage = createStorage();
        saveTestObject(storage);
        try {
            storage.delete(TestUtils.TEST_NAME);
            assertFalse(storage.contains(TestUtils.TEST_NAME));
        } catch (UnsupportedOperationException ignored) {}
    }

    @Test
    public void testDeleteAll() throws Exception {
        S storage = createStorage();
        saveTestObject(storage);
        try {
            storage.deleteAll();
            assertFalse(storage.contains(TestUtils.TEST_NAME));
        } catch (UnsupportedOperationException ignored) {}
    }

}
