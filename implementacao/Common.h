#ifndef COMMON_H
#define COMMON_H

#include <iostream>
#include <vector>
#include <string>

#include <QPushButton>
#include <QComboBox>
#include <QMessageBox>
#include <QString>

using namespace std;

void warningMessage(string str);
QPushButton * mountButton(string msg);
int isSettedVariable(QString var, QString msgErr);


#endif
