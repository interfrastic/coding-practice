//
//  main.c
//  structuringthedocument
//
//  Created by Michael Krueger on 11/19/18.
//  Copyright © 2018 Michael Krueger. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#define MAX_CHARACTERS 1005
#define MAX_PARAGRAPHS 5

struct word {
    char* data;
};

struct sentence {
    struct word* data;
    int word_count;//denotes number of words in a sentence
};

struct paragraph {
    struct sentence* data  ;
    int sentence_count;//denotes number of sentences in a paragraph
};

struct document {
    struct paragraph* data;
    int paragraph_count;//denotes number of paragraphs in a document
};

#define MAX_CHAR_COUNT 1000

/*
 * The one added to the maximum word count takes into account the fact that we
 * initialize the structure that points to the next word when we parse the
 * period that teminates a sentence, despite the fact that there might not be
 * another word.
 */

#define MAX_WORD_COUNT ((MAX_CHAR_COUNT / 2) + 1)

/*
 * Because each sentence could be one word, the maximum sentence count is the
 * same as the maximum word count; as with the maximum word count, we have added
 * one to take into account the fact that we initialize the structure that
 * points to the next sentence when we parse the period that teminates a
 * sentence, despite the fact that there might not be another sentence.
 */

#define MAX_SENTENCE_COUNT MAX_WORD_COUNT
#define MAX_PARAGRAPH_COUNT 5

struct document get_document(char* text) {
    char* p_data = malloc(MAX_CHAR_COUNT * sizeof (char));
    struct word* p_word = malloc(MAX_WORD_COUNT * sizeof (struct word));
    struct sentence* p_sentence = malloc(MAX_SENTENCE_COUNT
                                         * sizeof (struct sentence));
    struct paragraph* p_paragraph = malloc(MAX_PARAGRAPH_COUNT
                                           * sizeof (struct paragraph));
    struct document document;
    
    char c;
    char* p_letter = p_data;

    p_word->data = p_letter;
    
    p_sentence->data = p_word;
    p_sentence->word_count = 0;
    
    p_paragraph->data = p_sentence;
    p_paragraph->sentence_count = 0;
    
    document.data = p_paragraph;
    document.paragraph_count = 0;
    
    while ((c = *text++) != 0) {
        switch (c) {
            case ' ':
                *p_letter++ = 0;
                p_sentence->word_count++;
                
                (++p_word)->data = p_letter;

                break;
            case '.':
                *p_letter++ = 0;
                p_sentence->word_count++;
                
                (++p_word)->data = p_letter;

                p_paragraph->sentence_count++;
                
                (++p_sentence)->data = p_word;
                p_sentence->word_count = 0;
                
                break;
            case '\n':
                document.paragraph_count++;
                
                (++p_paragraph)->data = p_sentence;
                p_paragraph->sentence_count = 0;

                break;
            default:
                *p_letter++ = c;

                break;
        }
    }
    
    document.paragraph_count++;

    return document;
}

struct word kth_word_in_mth_sentence_of_nth_paragraph(struct document Doc, int k, int m, int n) {
    return *(((Doc.data + n - 1)->data + m - 1)->data + k - 1);
}

struct sentence kth_sentence_in_mth_paragraph(struct document Doc, int k, int m) {
    return *((Doc.data + m - 1)->data + k - 1);
}

struct paragraph kth_paragraph(struct document Doc, int k) {
    return *(Doc.data + k - 1);
}


void print_word(struct word w) {
    printf("%s", w.data);
}

void print_sentence(struct sentence sen) {
    for(int i = 0; i < sen.word_count; i++) {
        print_word(sen.data[i]);
        if (i != sen.word_count - 1) {
            printf(" ");
        }
    }
}

void print_paragraph(struct paragraph para) {
    for(int i = 0; i < para.sentence_count; i++){
        print_sentence(para.data[i]);
        printf(".");
    }
}

void print_document(struct document doc) {
    for(int i = 0; i < doc.paragraph_count; i++) {
        print_paragraph(doc.data[i]);
        if (i != doc.paragraph_count - 1)
            printf("\n");
    }
}

char* get_input_text() {
    int paragraph_count;
    scanf("%d", &paragraph_count);
    
    char p[MAX_PARAGRAPHS][MAX_CHARACTERS], doc[MAX_CHARACTERS];
    memset(doc, 0, sizeof(doc));
    getchar();
    for (int i = 0; i < paragraph_count; i++) {
        scanf("%[^\n]%*c", p[i]);
        strcat(doc, p[i]);
        if (i != paragraph_count - 1)
            strcat(doc, "\n");
    }
    
    char* returnDoc = (char*)malloc((strlen (doc)+1) * (sizeof(char)));
    strcpy(returnDoc, doc);
    return returnDoc;
}

int main()
{
    char* text = get_input_text();
    struct document Doc = get_document(text);
    
    int q;
    scanf("%d", &q);
    
    while (q--) {
        int type;
        scanf("%d", &type);
        
        if (type == 3){
            int k, m, n;
            scanf("%d %d %d", &k, &m, &n);
            struct word w = kth_word_in_mth_sentence_of_nth_paragraph(Doc, k, m, n);
            print_word(w);
        }
        
        else if (type == 2) {
            int k, m;
            scanf("%d %d", &k, &m);
            struct sentence sen= kth_sentence_in_mth_paragraph(Doc, k, m);
            print_sentence(sen);
        }
        
        else{
            int k;
            scanf("%d", &k);
            struct paragraph para = kth_paragraph(Doc, k);
            print_paragraph(para);
        }
        printf("\n");
    }
}
