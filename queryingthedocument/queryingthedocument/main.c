//
//  main.c
//  queryingthedocument
//
//  Created by Michael Krueger on 11/19/18.
//  Copyright © 2018 Michael Krueger. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include<assert.h>
#define MAX_CHARACTERS 1005
#define MAX_PARAGRAPHS 5

/******************************************************************************/

#define MAX_CHARACTER_COUNT 1000                    /* Given in problem. */
#define MAX_PARAGRAPH_COUNT 5                       /* Given in problem. */
#define MAX_TEXT_SIZE (MAX_CHARACTER_COUNT + 1)     /* NUL-terminated text. */
#define MAX_WORD_COUNT (MAX_CHARACTER_COUNT / 2)    /* One-character words. */
#define MAX_SENTENCE_COUNT MAX_WORD_COUNT           /* One-word sentences. */

void * mallocOrDie(size_t size) {
    void * const p = malloc(size);

    if (!p) {
        fprintf(stderr, "Out of memory\n");

        exit(1);
    }

    return p;
}

char * kth_word_in_mth_sentence_of_nth_paragraph(char **** document, int k,
                                                 int m, int n) {
    return document[n - 1][m - 1][k - 1];
}

char ** kth_sentence_in_mth_paragraph(char **** document, int k, int m) {
    return document[m - 1][k - 1];
}

char *** kth_paragraph(char **** document, int k) {
    return document[k - 1];
}

char **** get_document(char * pText) {
    int n, m, k; // Paragraph, sentence, and word indices.
    char **** document = mallocOrDie(sizeof(char ***) * MAX_PARAGRAPHS);
    char * pBuf = mallocOrDie(MAX_TEXT_SIZE);

    for (n = 0; n < MAX_PARAGRAPH_COUNT; n++) {
        document[n] = mallocOrDie(sizeof(char **) * MAX_SENTENCE_COUNT);
        for (m = 0; m < MAX_SENTENCE_COUNT; m++) {
            document[n][m] = mallocOrDie(sizeof(char *) * MAX_WORD_COUNT);
        }
    }

    n = m = k = 0;  /* First paragraph, sentence, and word. */
    char * pWord = NULL;
    char c;

    do {
        switch (c = *(pText++)) {
            case '\n':  /* End of this paragraph. */
                *(pBuf++) = '\0';   /* Terminate this word with NUL. */
                document[n++][m][k] = pWord;    /* Add this word. */
                pWord = NULL;  /* Start new word. */
                k = m = 0;  /* First word and sentence in new paragraph. */
                break;
            case '.':   /* End of this sentence. */
                *(pBuf++) = '\0';   /* Terminate this word with NUL. */
                document[n][m++][k] = pWord;    /* Add this word. */
                pWord = NULL;  /* Start new word. */
                k = 0;  /* First word in new sentence. */
                break;
            case ' ':   /* End of this word. */
                *(pBuf++) = '\0';   /* Terminate this word with NUL. */
                document[n][m][k++] = pWord;    /* Add this word. */
                pWord = NULL;  /* Start new word. */
                break;
            default:    /* Add character to this word. */
                if (!pWord) {
                    pWord = pBuf;   /* Remember where this new word started. */
                }
                *(pBuf++) = c;  /* Will be NUL at end of text. */
                break;
        }
    } while (c);    /* NUL terminates the enitre text. */

    return document;
}

/******************************************************************************/

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

void print_word(char* word) {
    printf("%s", word);
}

void print_sentence(char** sentence) {
    int word_count;
    scanf("%d", &word_count);
    for(int i = 0; i < word_count; i++){
        printf("%s", sentence[i]);
        if( i != word_count - 1)
            printf(" ");
    }
}

void print_paragraph(char*** paragraph) {
    int sentence_count;
    scanf("%d", &sentence_count);
    for (int i = 0; i < sentence_count; i++) {
        print_sentence(*(paragraph + i));
        printf(".");
    }
}

int main()
{
    char* text = get_input_text();
    char**** document = get_document(text);
    
    int q;
    scanf("%d", &q);
    
    while (q--) {
        int type;
        scanf("%d", &type);
        
        if (type == 3){
            int k, m, n;
            scanf("%d %d %d", &k, &m, &n);
            char* word = kth_word_in_mth_sentence_of_nth_paragraph(document, k, m, n);
            print_word(word);
        }
        
        else if (type == 2){
            int k, m;
            scanf("%d %d", &k, &m);
            char** sentence = kth_sentence_in_mth_paragraph(document, k, m);
            print_sentence(sentence);
        }
        
        else{
            int k;
            scanf("%d", &k);
            char*** paragraph = kth_paragraph(document, k);
            print_paragraph(paragraph);
        }
        printf("\n");
    }
}
