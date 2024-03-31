#include <iostream>
#include <cstring>
using namespace std;

class EMail {
private:
    char to[100];
    char from[100];
    char subject[200];
    char body[1000];
public:
    EMail(char * to, char * from, char * subject, char * body) {
        strcpy(to,to);
        strcpy(from,from);
        strcpy(subject,subject);
        strcpy(body,body);
    }
    ~EMail() {
    }
    void setTo(char * n) {
        strcpy(to,n);
    }
    void setFrom(char * n) {
        strcpy(from, n);
    }
    void setSubject(char * n) {
        strcpy(subject,n);
    }
    void setBody(char * n) {
        strcpy(body,n);
    }
    void print() {
        cout << "To: " << to << endl;
        cout << "From: " << from << endl;
        cout << "Subject: " << subject << endl;
        cout << "Body: " << body << endl;
    }
};

bool checkEMail (char * address) {
    int i = 0;
    while (* address != 0) {
        if ('@' == *address++){
            i++;
        }
    }
    return (1 == i);
}

int main() {
    char to[100],from[100],subject[200],body[1000];
    cout << "To: " << endl;
    cin >> to;
    if (checkEMail(to)) {
        cout << "From: " << endl;
        cin >> from;
        cout << "Subject: " << endl;
        cin >> subject;
        cout << "Body: " << endl;
        cin >> body;
        EMail example(to,from,subject,body);
        cout << "Sent: " << endl;
        example.print();
    } else {
        cout << "Invalid email address!" << endl;
    }
    return 0;
}
