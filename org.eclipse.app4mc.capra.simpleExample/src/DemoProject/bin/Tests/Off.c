
#ifdef UNIT_TEST
#define get_app mock_get_app
#endif

#include "actions.h"
#include "signals.h"
#include "app.h"

#include <kern/kern.h>
#include <stdio.h>
#include <inttypes.h>
#include <string.h>

int action_parse_uint32 (uint32_t * value, const char * name, char * str)
{
   int items;
   char fmt[80];

   snprintf (fmt, sizeof(fmt), "%s=%%" PRIu32, name);
   items = sscanf (str, fmt, value);
   return items == 1;
}

int action_parse_float (float * value, const char * name, char * str)
{
   int items;
   char fmt[80];

   snprintf (fmt, sizeof(fmt), "%s=%%f", name);
   items = sscanf (str, fmt, value);
   return items == 1;
}

int action_parse_bool (uint32_t * value, const char * name, char * str)
{
   char * p;

   p = strchr(str, '=');
   if (p == NULL)
      return 0;

   p += 1;
   if (strcmp(p, "true") == 0)
   {
      *value = 1;
      return 1;
   }
   else if (strcmp(p, "false") == 0)
   {
      *value = 0;
      return 1;
   }

   return 0;
}

int action_parse_mode (move_mode_t * value, const char * name, char * str)
{
   char * p;

   p = strchr(str, '=');
   if (p == NULL)
      return 0;

   *value = params_mode_get_mode (p + 1);
   return *value != MODE_ILLEGAL;
}


action_t * actions_lookup(action_t * actions, const char * name)
{
   action_t * action = &actions[0];

   // The number of actions is expected to be small, do a linear search
   while (action->name != NULL)
   {
      if (strcmp(action->name, name) == 0)
      {
         return action;
      }
      action++;
   }

   /* Action not found */
   return NULL;
}

int action_move (int argc, char * argv[])
{
   struct sig_move * sig;
   int success;
   move_mode_t mode;
   float pos;
   float vel;
   float acc;

   if (argc != 5)
      return 0;

   success = action_parse_mode (&mode, "mode", argv[1]);
   if (!success)
      return 0;

   success = action_parse_float (&pos, "pos", argv[2]);
   if (!success)
      return 0;

   success = action_parse_float (&vel, "vel", argv[3]);
   if (!success)
      return 0;

   success = action_parse_float (&acc, "acc", argv[4]);
   if (!success)
      return 0;

   sig = (struct sig_move *)sig_create (SIG_MOVE, sizeof(*sig));
   sig->mode = mode;
   sig->position = pos;
   sig->velocity = vel;
   sig->acceleration = acc;
   sig_send (get_app(), (sig_t *)sig);

   return 1;
}

int action_stop (int argc, char * argv[])
{
   struct sig_stop * sig;
   uint32_t is_quick_stop = 0;

   if (argc > 2)
      return 0;

   /* Optional quickstop parameter */
   if (argc == 2)
   {
      int success = action_parse_bool (&is_quick_stop, "quickstop", argv[1]);
      if (!success)
         return 0;
   }

   sig = (struct sig_stop *)sig_create (SIG_STOP, sizeof(*sig));
   sig->is_quick_stop = is_quick_stop;
   sig_send (get_app(), (sig_t *)sig);

   return 1;
}

int action_sequence (int argc, char * argv[])
{
   struct sig_sequence * sig;
   int success;
   uint32_t id;

   if (argc != 2)
      return 0;

   success = action_parse_uint32 (&id, "id", argv[1]);
   if (!success)
      return 0;

   sig = (struct sig_sequence *)sig_create (SIG_SEQUENCE, sizeof(*sig));
   sig->id = id;
   sig_send (get_app(), (sig_t *)sig);

   return 1;
}


