/*
 * Copyright 2009 Denys Pavlov, Igor Azarnyi
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.yes.cart.domain.query.impl;

import org.apache.lucene.search.Query;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * User: denispavlov
 * Date: 04/12/2014
 * Time: 18:41
 */
public class AttributeSkuSearchQueryBuilderTest {

    @Test
    public void testCreateStrictQueryNull() throws Exception {

        final Query query = new AttributeSkuSearchQueryBuilder().createStrictQuery(10L, "size", null);
        assertNull(query);

    }

    @Test
    public void testCreateStrictQueryBlank() throws Exception {

        final Query query = new AttributeSkuSearchQueryBuilder().createStrictQuery(10L, "size", "   ");
        assertNull(query);

    }

    @Test
    public void testCreateStrictQuerySingle() throws Exception {

        final Query query = new AttributeSkuSearchQueryBuilder().createStrictQuery(10L, "size", "30");
        assertNotNull(query);
        assertEquals("facet_size:30^3.5", query.toString());

    }

    @Test
    public void testCreateStrictQueryRange() throws Exception {

        final Query query = new AttributeSkuSearchQueryBuilder().createStrictQuery(10L, "size", "30-_-50");
        assertNotNull(query);
        assertEquals("facet_size:[30 TO 50}^3.5", query.toString());

    }

    @Test
    public void testCreateStrictQueryRangeFrom() throws Exception {

        final Query query = new AttributeSkuSearchQueryBuilder().createStrictQuery(10L, "size", "30-_-");
        assertNotNull(query);
        assertEquals("facet_size:[30 TO *}^3.5", query.toString());

    }

    @Test
    public void testCreateStrictQueryRangeTo() throws Exception {

        final Query query = new AttributeSkuSearchQueryBuilder().createStrictQuery(10L, "size", "-_-50");
        assertNotNull(query);
        assertEquals("facet_size:[* TO 50}^3.5", query.toString());

    }


    @Test
    public void testCreateRelaxedQueryNull() throws Exception {

        final Query query = new AttributeSkuSearchQueryBuilder().createRelaxedQuery(10L, "size", null);
        assertNull(query);

    }

    @Test
    public void testCreateRelaxedQueryBlank() throws Exception {

        final Query query = new AttributeSkuSearchQueryBuilder().createRelaxedQuery(10L, "size", "   ");
        assertNull(query);

    }


    @Test
    public void testCreateRelaxedQuerySingle() throws Exception {

        final Query query = new AttributeSkuSearchQueryBuilder().createRelaxedQuery(10L, "size", "30");
        assertNotNull(query);
        assertEquals("facet_size:30^3.5", query.toString());


    }


    @Test
    public void testCreateRelaxedQueryRange() throws Exception {

        final Query query = new AttributeSkuSearchQueryBuilder().createRelaxedQuery(10L, "size", "30-_-50");
        assertNotNull(query);
        assertEquals("facet_size:[30 TO 50}^3.5", query.toString());

    }

    @Test
    public void testCreateRelaxedQueryRangeFrom() throws Exception {

        final Query query = new AttributeSkuSearchQueryBuilder().createRelaxedQuery(10L, "size", "30-_-");
        assertNotNull(query);
        assertEquals("facet_size:[30 TO *}^3.5", query.toString());

    }

    @Test
    public void testCreateRelaxedQueryRangeTo() throws Exception {

        final Query query = new AttributeSkuSearchQueryBuilder().createRelaxedQuery(10L, "size", "-_-50");
        assertNotNull(query);
        assertEquals("facet_size:[* TO 50}^3.5", query.toString());

    }



}
